package com.sample.orders.dataprovider.configuration;


import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories("com.sample.orders.dataprovider.database")
@EntityScan("com.sample.orders.dataprovider.database")
public class PersistenceConfig {

}
