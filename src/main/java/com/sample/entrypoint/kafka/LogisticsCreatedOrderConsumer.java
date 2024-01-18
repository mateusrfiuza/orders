package com.sample.entrypoint.kafka;

import com.sample.domain.logistics.usecase.CreateInvoiceUseCase;
import com.sample.events.OrderCreatedSchema;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;

import java.util.Objects;

import static com.sample.entrypoint.kafka.config.KafkaConsumerConfig.LOGISTICS_CONTAINER_FACTORY;
import static com.sample.entrypoint.kafka.config.KafkaConsumerConfig.LOGISTICS_GROUP_ID;

@Component
public class LogisticsCreatedOrderConsumer implements GenericKafkaConsumer<String,OrderCreatedSchema> {

    private static final Logger logger = LoggerFactory.getLogger(MembershipCreatedOrderConsumer.class);
    private static final String TOPIC = "order_created_event";
    private static final String MAX_CONCURRENCY = "1";

    private final CreateInvoiceUseCase createInvoiceUseCase;

    public LogisticsCreatedOrderConsumer(final CreateInvoiceUseCase createInvoiceUseCase) {
        this.createInvoiceUseCase = createInvoiceUseCase;
    }

    @Override
    @KafkaListener(
            topics = {TOPIC},
            containerFactory = LOGISTICS_CONTAINER_FACTORY,
            concurrency = MAX_CONCURRENCY,
            groupId = LOGISTICS_GROUP_ID
    )
    public void consume(final ConsumerRecord<String,OrderCreatedSchema> consumerRecord, final Acknowledgment acknowledgment) {

        Objects.requireNonNull(consumerRecord, "OrderCreatedSchema cannot be null.");

        logger.info("Logistics consumer received Order Created Event [ {} ], from: [ ORDERS-SERVICE ]", consumerRecord);

        createInvoiceUseCase.execute();

        acknowledgment.acknowledge();

    }

    @Override
    public Logger getLogger() {
        return logger;
    }

}
