package com.sample.dataprovider.repository;

import com.sample.dataprovider.database.OrderDAO;
import com.sample.dataprovider.database.entity.OrderEntity;
import com.sample.dataprovider.database.entity.OrderItemEntity;
import com.sample.domain.membership.dataprovider.repository.MembershipRepository;
import com.sample.domain.orders.dataprovider.repository.OrderRepository;
import com.sample.domain.orders.entity.Order;
import org.springframework.stereotype.Repository;

import java.util.stream.Collectors;

@Repository
public class MembershipRepositoryImpl implements MembershipRepository {


    @Override
    public void save() {
        System.out.println("Saving membership");
    }
}
