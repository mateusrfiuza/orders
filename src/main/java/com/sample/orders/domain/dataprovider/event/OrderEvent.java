package com.sample.orders.domain.dataprovider.event;

import com.sample.orders.domain.entity.OrderStatus;

public abstract sealed class OrderEvent permits OrderCreatedEvent {

    private final String orderId;
    private final OrderStatus orderStatus;

    public OrderEvent(String orderId, OrderStatus orderStatus) {
        this.orderId = orderId;
        this.orderStatus = orderStatus;
    }

    public String getOrderId() {
        return orderId;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

}
