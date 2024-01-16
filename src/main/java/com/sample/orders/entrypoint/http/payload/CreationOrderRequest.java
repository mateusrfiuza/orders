package com.sample.orders.entrypoint.http.payload;

import com.sample.orders.domain.usecase.commands.CreateOrderCommand;
import com.sample.orders.domain.usecase.commands.ItemCreateOrderCommand;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.time.Instant;
import java.util.Set;
import java.util.stream.Collectors;

public record CreationOrderRequest(
        @NotEmpty(message = "sellerId cannot be null")
        String sellerId,
        @NotEmpty(message = "customer cannot be null")
        String customerId,
        @NotEmpty(message = "items should have at least one item")
        Set<Item> items,
        @NotNull(message = "orderDate cannot be null")
        Instant orderDate,
        @NotNull(message = "currency cannot be null")
        String currency
) {

    public CreateOrderCommand toCommand() {
        final var commandItems = items().stream()
                .map(item -> new ItemCreateOrderCommand(item.quantity(), item.itemId()))
                .collect(Collectors.toSet());

        return new CreateOrderCommand(sellerId(), customerId(), commandItems, orderDate(), currency());
    }
}


record Item(
        @Min(value = 1, message = "quantity should be at least one")
        Long quantity,
        @NotEmpty(message = "itemId cannot be null")
        String itemId
) {
}
