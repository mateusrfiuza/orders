package com.sample.entrypoint.kafka;

import com.sample.orders.events.OrderCreatedSchema;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;

import java.util.Objects;

import static com.sample.entrypoint.kafka.config.KafkaConsumerConfig.MEMBERSHIP_CONTAINER_FACTORY;

@Component
public class MembershipCreatedOrderConsumer implements GenericKafkaConsumer<String, OrderCreatedSchema> {

    private static final Logger logger = LoggerFactory.getLogger(MembershipCreatedOrderConsumer.class);
    private static final String TOPIC = "order_created_event";
    private static final String MAX_CONCURRENCY = "1";

    @Override
    @KafkaListener(topics = {TOPIC}, containerFactory = MEMBERSHIP_CONTAINER_FACTORY, concurrency = MAX_CONCURRENCY)
    public void consume(final ConsumerRecord<String, OrderCreatedSchema> consumerRecord, final Acknowledgment acknowledgment) {

        Objects.requireNonNull(consumerRecord, "OrderCreatedSchema cannot be null.");

        logger.info("Membership consumer received Order Created Event: [ {} ], from: [ ORDERS-SERVICE ]", consumerRecord);

        System.out.println(consumerRecord);
        acknowledgment.acknowledge();

    }

    @Override
    public Logger getLogger() {
        return logger;
    }

}
