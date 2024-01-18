package com.sample.dataprovider.event;

import com.sample.dataprovider.producer.KafkaProducer;
import com.sample.dataprovider.producer.OrderEventFactory;
import com.sample.domain.orders.dataprovider.event.OrderEvent;
import com.sample.domain.orders.dataprovider.event.OrderNotifier;
import org.springframework.stereotype.Component;

@Component
public class OrderNotifierImpl implements OrderNotifier {

    private final KafkaProducer kafkaProducer;
    private final OrderEventFactory eventFactory;

    public OrderNotifierImpl(final KafkaProducer kafkaProducer,
                             final OrderEventFactory eventFactory) {
        this.kafkaProducer = kafkaProducer;
        this.eventFactory = eventFactory;
    }


    @Override
    public void notify(OrderEvent event) {
        var message = eventFactory.create(event);
        kafkaProducer.send(message);
    }


}
