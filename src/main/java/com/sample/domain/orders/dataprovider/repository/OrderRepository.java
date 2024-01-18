package com.sample.domain.orders.dataprovider.repository;

import com.sample.domain.orders.entity.Order;

public interface OrderRepository {

    String saveOrder(Order order);

}
