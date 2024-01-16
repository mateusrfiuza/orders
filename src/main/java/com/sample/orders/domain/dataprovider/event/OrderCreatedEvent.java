package com.sample.orders.domain.dataprovider.event;

import com.sample.orders.domain.entity.OrderStatus;

public final class OrderCreatedEvent extends OrderEvent {


    public OrderCreatedEvent(final String orderId) {
        super(orderId, OrderStatus.CREATED);
    }


}
