package com.sample.domain.orders.dataprovider.event;

import com.sample.domain.orders.entity.OrderStatus;

public final class OrderCreatedEvent extends OrderEvent {


    public OrderCreatedEvent(final String orderId) {
        super(orderId, OrderStatus.CREATED);
    }


}
