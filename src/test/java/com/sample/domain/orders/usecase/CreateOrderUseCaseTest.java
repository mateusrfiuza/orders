package com.sample.domain.orders.usecase;

import com.sample.domain.orders.dataprovider.event.OrderCreatedEvent;
import com.sample.domain.orders.dataprovider.event.OrderNotifier;
import com.sample.domain.orders.dataprovider.repository.OrderRepository;
import com.sample.domain.orders.entity.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static com.sample.fixture.CreateOrderCommandFixture.gimmeValidCreateOrderCommand;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CreateOrderUseCaseTest {

    @Mock
    private OrderRepository orderRepository;

    @Mock
    private OrderNotifier orderNotifier;

    @InjectMocks
    private CreateOrderUseCase createOrderUseCase;

    @Captor
    private ArgumentCaptor<Order> orderCaptor;

    @Test
    void should_execute_creation_order() {

        // Given
        var command = gimmeValidCreateOrderCommand();
        when(orderRepository.saveOrder(any())).thenReturn("1");

        // When
        var orderId = createOrderUseCase.execute(command);

        // Then
        assertEquals("1", orderId);

        verify(orderRepository).saveOrder(orderCaptor.capture());
        var capturedOrder = orderCaptor.getValue();
        assertEquals(command.sellerId(), capturedOrder.getSellerID());
        assertEquals(command.customerId(), capturedOrder.getCustomerId());
        assertEquals(command.items().size(), capturedOrder.getOrderItems().size());

        verify(orderNotifier).notify(any(OrderCreatedEvent.class));
    }


}
