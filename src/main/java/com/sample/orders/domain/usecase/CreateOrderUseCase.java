package com.sample.orders.domain.usecase;

import com.sample.orders.domain.dataprovider.event.OrderCreatedEvent;
import com.sample.orders.domain.entity.Order;
import com.sample.orders.domain.dataprovider.event.OrderNotifier;
import com.sample.orders.domain.usecase.commands.CreateOrderCommand;
import com.sample.orders.domain.dataprovider.repository.OrderRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class CreateOrderUseCase {

    private final OrderRepository orderStorageRepository;
    private final OrderNotifier orderNotifier;

    public CreateOrderUseCase(final OrderRepository orderRepository,
                              final OrderNotifier orderNotifier) {
        this.orderStorageRepository = orderRepository;
        this.orderNotifier = orderNotifier;
    }


    @Transactional()
    public String execute(final CreateOrderCommand command) {
        var orderId = orderStorageRepository.saveOrder(new Order());
        orderNotifier.notify(new OrderCreatedEvent(orderId));
        return orderId;
    }

}
