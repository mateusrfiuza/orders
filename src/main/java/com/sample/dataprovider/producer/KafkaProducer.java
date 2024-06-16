package com.sample.dataprovider.producer;

import org.apache.avro.generic.GenericRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.Objects;
import java.util.UUID;

@Component
public class KafkaProducer implements GenericProducer<Message<GenericRecord>> {

    private static final Logger logger = LoggerFactory.getLogger(GenericProducer.class);

    private final KafkaTemplate<String, Message<GenericRecord>> kafkaTemplate;

    public KafkaProducer(final KafkaTemplate<String, Message<GenericRecord>> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @Override
    @Async
    public void send(final Message<GenericRecord> value) {
        final var key = value.getHeaders().get(KafkaHeaders.KEY);
        final var topic = Objects.requireNonNull(value.getHeaders().get(KafkaHeaders.TOPIC)).toString();
        final var messageId = UUID.fromString(Objects.requireNonNull(value.getHeaders().get(MessageHeaders.ID)).toString());
        logger.info("Sending message to topic [ {} ], key [ {} ], messageId[ {} ]", topic, key, messageId);
        kafkaTemplate.send(value);
    }

}
