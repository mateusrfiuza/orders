package com.sample.entrypoint.kafka.config;

import io.confluent.kafka.serializers.KafkaAvroDeserializer;
import io.confluent.kafka.serializers.KafkaAvroDeserializerConfig;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.boot.ssl.DefaultSslBundleRegistry;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.listener.ContainerProperties;
import org.springframework.kafka.listener.DefaultErrorHandler;

import java.util.UUID;

@Configuration
@EnableKafka
public class KafkaConsumerConfig {

    public static final String MEMBERSHIP_CONTAINER_FACTORY = "MEMBERSHIP_CONTAINER_FACTORY";
    public static final String MEMBERSHIP_GROUP_ID = "MEMBERSHIP_CONTAINER_FACTORY";
    public static final String LOGISTICS_CONTAINER_FACTORY = "LOGISTICS_CONTAINER_FACTORY";
    public static final String LOGISTICS_GROUP_ID = "LOGISTICS_GROUP_ID";

    @Value("${spring.kafka.bootstrap-servers}")
    private String kafkaBootstrapServers;

    @Value("${spring.kafka.properties.schema.registry.url}")
    private String schemaRegistryURL;

    public ConsumerFactory<String, String> consumerFactory(final String groupId) {
        final var kafkaProperties = new KafkaProperties();
        final var props = kafkaProperties.buildConsumerProperties((new DefaultSslBundleRegistry()));

        props.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, false);
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaBootstrapServers);
        props.put(ConsumerConfig.GROUP_ID_CONFIG, groupId);
        props.put(ConsumerConfig.CLIENT_ID_CONFIG, UUID.randomUUID().toString());
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, KafkaAvroDeserializer.class);
        props.put(KafkaAvroDeserializerConfig.SPECIFIC_AVRO_READER_CONFIG, "true");
        props.put(KafkaAvroDeserializerConfig.SCHEMA_REGISTRY_URL_CONFIG, schemaRegistryURL);

        return new DefaultKafkaConsumerFactory<>(props);
    }

    @Bean(MEMBERSHIP_CONTAINER_FACTORY)
    public ConcurrentKafkaListenerContainerFactory<String, String> membershipContainerFactory() {
        final var factory = new ConcurrentKafkaListenerContainerFactory<String, String>();

        factory.setCommonErrorHandler(new DefaultErrorHandler());
        factory.setConsumerFactory(consumerFactory(MEMBERSHIP_GROUP_ID));
        factory.getContainerProperties().setAckMode(ContainerProperties.AckMode.MANUAL_IMMEDIATE);

        return factory;
    }


    @Bean(LOGISTICS_CONTAINER_FACTORY)
    public ConcurrentKafkaListenerContainerFactory<String, String> logisticsContainerFactory() {
        final var factory = new ConcurrentKafkaListenerContainerFactory<String, String>();

        factory.setCommonErrorHandler(new DefaultErrorHandler());
        factory.setConsumerFactory(consumerFactory(LOGISTICS_GROUP_ID));
        factory.getContainerProperties().setAckMode(ContainerProperties.AckMode.MANUAL_IMMEDIATE);

        return factory;
    }

}
