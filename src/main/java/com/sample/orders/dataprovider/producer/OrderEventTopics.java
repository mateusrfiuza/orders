package com.sample.orders.dataprovider.producer;


import java.util.Map;

import com.sample.orders.domain.dataprovider.event.OrderEvent;
import jakarta.validation.constraints.NotEmpty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;


@Validated
@ConfigurationProperties("order.event")
public class OrderEventTopics {

    @NotEmpty
    private Map<String, @NotEmpty String> destinationTopic;

    public String getDestinationTopic(final OrderEvent value) {
        return destinationTopic.get(value.getClass().getSimpleName());
    }

    public void setDestinationTopic(final Map<String, String> destinationTopic) {
        this.destinationTopic = destinationTopic;
    }
}

