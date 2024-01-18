package com.sample.dataprovider.configuration;


import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories("com.sample.dataprovider.database")
@EntityScan("com.sample.dataprovider.database")
public class PersistenceConfig {

}
