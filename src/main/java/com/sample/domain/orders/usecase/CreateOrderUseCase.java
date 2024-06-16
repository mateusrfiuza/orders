package com.sample.domain.orders.usecase;

import com.sample.domain.orders.dataprovider.event.OrderCreatedEvent;
import com.sample.domain.orders.dataprovider.event.OrderNotifier;
import com.sample.domain.orders.dataprovider.repository.OrderRepository;
import com.sample.domain.orders.entity.Order;
import com.sample.domain.orders.usecase.commands.CreateOrderCommand;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class CreateOrderUseCase {

    private final OrderRepository orderRepository;
    private final OrderNotifier orderNotifier;

    public CreateOrderUseCase(final OrderRepository orderRepository,
                              final OrderNotifier orderNotifier) {
        this.orderRepository = orderRepository;
        this.orderNotifier = orderNotifier;
    }


    @Transactional
    public String execute(final CreateOrderCommand command) {
        final var order = Order.fromCreateOrderCommand(command);
        var orderId = orderRepository.saveOrder(order);
        orderNotifier.notify(new OrderCreatedEvent(orderId));
        return orderId;
    }


}
