package com.sample.dataprovider.producer;

import com.sample.domain.orders.dataprovider.event.OrderCreatedEvent;
import com.sample.domain.orders.dataprovider.event.OrderEvent;
import com.sample.domain.orders.entity.OrderStatus;
import com.sample.orders.events.OrderCreatedSchema;
import org.apache.avro.generic.GenericRecord;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.support.GenericMessage;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Objects;
import java.util.UUID;

@Component
public class OrderEventFactory {

    private final OrderEventTopics topics;

    private static final String TOPIC_HEADER = KafkaHeaders.TOPIC;
    private static final String KEY_HEADER = KafkaHeaders.KEY;

    public OrderEventFactory(final OrderEventTopics topics) {
        this.topics = topics;
    }

    public GenericMessage<GenericRecord> create(final OrderEvent event) {
        Objects.requireNonNull(event, "Event cannot be null");

        final var topic = topics.getDestinationTopic(event);
        final var avro = createAvroRecord(event);
        final var headers = Map.of(
                TOPIC_HEADER, topic,
                KEY_HEADER, avro.get("orderId")
        );

        return new GenericMessage<>(avro, headers);
    }

    private GenericRecord createAvroRecord(final OrderEvent event) {
        return switch (event.getOrderStatus()) {
            case OrderStatus.CREATED -> createOrderCreatedAvroRecord((OrderCreatedEvent) event);
            default -> throw new IllegalArgumentException("Unsupported event type");
        };
    }

    private GenericRecord createOrderCreatedAvroRecord(OrderCreatedEvent event) {
        return OrderCreatedSchema.newBuilder()
                .setOrderId(event.getOrderId())
                .setCustomerId(UUID.randomUUID())
                .setSellerId(UUID.randomUUID())
                .build();
    }

}
