package com.sample.orders.domain.dataprovider.event;

public interface OrderNotifier {


    void notify(OrderEvent event);
}
