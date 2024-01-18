package com.sample.domain.orders.dataprovider.event;

public interface OrderNotifier {


    void notify(OrderEvent event);
}
