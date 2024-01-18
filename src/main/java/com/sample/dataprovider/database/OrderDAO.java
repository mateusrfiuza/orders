package com.sample.dataprovider.database;

import com.sample.dataprovider.database.entity.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderDAO extends JpaRepository<OrderEntity, Long> {
}
