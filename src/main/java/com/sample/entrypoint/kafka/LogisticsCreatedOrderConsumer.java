//package com.sample.entrypoint.kafka;
//
//import com.sample.orders.events.OrderCreatedSchema;
//import org.apache.kafka.clients.consumer.ConsumerRecord;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.kafka.annotation.KafkaListener;
//import org.springframework.kafka.support.Acknowledgment;
//
//import java.util.Objects;
//
//public class LogisticsCreatedOrderConsumer implements GenericKafkaConsumer<String, OrderCreatedSchema> {
//
//    private static final Logger logger = LoggerFactory.getLogger(LogisticsCreatedOrderConsumer.class);
//    private static final String TOPIC = "order_created_event";
//    private static final String MAX_CONCURRENCY = "${spring.kafka.listener.concurrency:}";
//
//
//    @Override
//    @KafkaListener(topics = TOPIC, concurrency = MAX_CONCURRENCY, groupId = "logistics.groupId")
//    public void consume(final ConsumerRecord<String, OrderCreatedSchema> consumerRecord, final Acknowledgment acknowledgment) {
//        final var orderCreatedEventSchema = consumerRecord.value();
//
//        Objects.requireNonNull(orderCreatedEventSchema, "OrderCreatedSchema cannot be null.");
//
//        logger.info("Processing a order that was created: [ {} ], from: [ ORDERS-SERVICE ]", orderCreatedEventSchema);
//
//        System.out.println(orderCreatedEventSchema);
//        acknowledgment.acknowledge();
//
//    }
//
//    @Override
//    public Logger getLogger() {
//        return logger;
//    }
//
//}
