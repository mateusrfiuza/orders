package com.sample.dataprovider.repository;

import com.sample.dataprovider.database.entity.OrderItemEntity;
import com.sample.dataprovider.database.OrderDAO;
import com.sample.dataprovider.database.entity.OrderEntity;
import com.sample.domain.orders.entity.Order;
import com.sample.domain.orders.dataprovider.repository.OrderRepository;
import org.springframework.stereotype.Repository;

import java.util.stream.Collectors;

@Repository
public class OrderRepositoryImpl implements OrderRepository {

    private final OrderDAO orderDAO;

    public OrderRepositoryImpl(final OrderDAO orderDAO) {
        this.orderDAO = orderDAO;
    }

    @Override
    public String saveOrder(final Order order) {
        OrderEntity orderEntity = new OrderEntity();
        orderEntity.setCustomerId(order.getCustomerId());
        orderEntity.setSellerID(order.getSellerID());
        orderEntity.setStatus(order.getStatus().name());

        var orderItemEntities = order.getOrderItems().stream()
                .map(orderItem -> {
                    OrderItemEntity orderItemEntity = new OrderItemEntity();
                    orderItemEntity.setOrder(orderEntity);
                    orderItemEntity.setProductId(orderItem.getProductId());
                    return orderItemEntity;
                })
                .collect(Collectors.toList());

        orderEntity.addItems(orderItemEntities);

        OrderEntity savedOrderEntity = orderDAO.save(orderEntity);

        return savedOrderEntity.getId().toString();
    }
}
