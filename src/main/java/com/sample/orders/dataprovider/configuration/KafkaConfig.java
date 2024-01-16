package com.sample.orders.dataprovider.configuration;

import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.boot.ssl.DefaultSslBundleRegistry;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

import java.util.Map;

@EnableKafka
@Configuration
@Profile("!test")
public class KafkaConfig {

    private static final int MAX_PUBLISH_ATTEMPTS = Integer.MAX_VALUE;
    private static final int MAX_IN_FLIGHT_REQUESTS_PER_CONNECTION = 1;
    private static final int RETRY_BACKOFF_MS_CONFIG = 3_000;

    public static final String CONTAINER_FACTORY = "kafkaListenerContainerFactory";

    @Value("${kafka.bootstrap-servers}")
    private String kafkaBootstrapServers;

    @Value("${kafka.schema-registry-url}")
    private String schemaRegistryURL;

    @Value("${kafka.request.timeout}")
    private Integer kafkaMaxMillisTimeout;

    private final KafkaProperties properties;


    public KafkaConfig(final KafkaProperties properties) {
        this.properties = properties;
    }



    @Bean
    public <T1, T2> ProducerFactory<T1, T2> producerFactory() {
        final Map<String, Object> configProps = properties.buildProducerProperties(new DefaultSslBundleRegistry());
        configProps.put(ProducerConfig.REQUEST_TIMEOUT_MS_CONFIG, kafkaMaxMillisTimeout);
        configProps.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaBootstrapServers);
        configProps.put(ProducerConfig.MAX_BLOCK_MS_CONFIG, kafkaMaxMillisTimeout);
        configProps.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
//        configProps.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, KafkaAvroSerializer.class);
//        configProps.put(KafkaAvroSerializerConfig.SCHEMA_REGISTRY_URL_CONFIG, schemaRegistryURL);

        // Only one in-flight messages per Kafka broker connection
        configProps.put(ProducerConfig.MAX_IN_FLIGHT_REQUESTS_PER_CONNECTION, MAX_IN_FLIGHT_REQUESTS_PER_CONNECTION);
        configProps.put(ProducerConfig.RETRIES_CONFIG, MAX_PUBLISH_ATTEMPTS);

        // Only retry after three seconds.
        configProps.put(ProducerConfig.RETRY_BACKOFF_MS_CONFIG, RETRY_BACKOFF_MS_CONFIG);
        return new DefaultKafkaProducerFactory<>(configProps);
    }

    @Bean
    public KafkaTemplate kafkaTemplate() {
        return new KafkaTemplate<>(producerFactory());
    }

//    @Bean(CONTAINER_FACTORY)
//    public KafkaListenerContainerFactory suspiciousCustomerEventListener() {
//        final ConcurrentKafkaListenerContainerFactory<String, String> factory = new ConcurrentKafkaListenerContainerFactory<>();
//
//        factory.setCommonErrorHandler(new DefaultErrorHandler());
//        factory.getContainerProperties().setAckMode(MANUAL_IMMEDIATE);
//
//        return factory;
//    }

}
