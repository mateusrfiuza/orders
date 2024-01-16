package com.sample.orders.dataprovider.database;

import com.sample.orders.dataprovider.database.entity.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderDAO extends JpaRepository<OrderEntity, Long> {
}
