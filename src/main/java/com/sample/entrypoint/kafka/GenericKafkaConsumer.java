package com.sample.entrypoint.kafka;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.support.Acknowledgment;

import org.slf4j.Logger;

public interface GenericKafkaConsumer<K, V> {

    Logger getLogger();

    void consume(ConsumerRecord<K, V> consumerRecord, Acknowledgment acknowledgment);

}
