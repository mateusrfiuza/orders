package com.sample.orders.domain.dataprovider.repository;

import com.sample.orders.domain.entity.Order;

public interface OrderRepository {

    String saveOrder(Order order);

}
