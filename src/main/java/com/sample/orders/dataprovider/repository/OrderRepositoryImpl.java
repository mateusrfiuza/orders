package com.sample.orders.dataprovider.repository;

import com.sample.orders.dataprovider.database.OrderDAO;
import com.sample.orders.dataprovider.database.entity.OrderEntity;
import com.sample.orders.domain.entity.Order;
import com.sample.orders.domain.dataprovider.repository.OrderRepository;
import org.springframework.stereotype.Repository;

@Repository
public class OrderRepositoryImpl implements OrderRepository {

    private final OrderDAO orderDAO;

    public OrderRepositoryImpl(final OrderDAO orderDAO) {
        this.orderDAO = orderDAO;
    }

    @Override
    public String saveOrder(final Order order) {
        var entity = new OrderEntity();
        entity.setCustomerId("customerId");
        entity.setSellerID("sellerId");
        entity.setStatus("CREATED");
        return orderDAO.save(entity).getId().toString();
    }
}
