package com.sample.entrypoint.http.payload;

import com.sample.domain.orders.usecase.commands.CreateOrderCommand;
import com.sample.domain.orders.usecase.commands.ItemCreateOrderCommand;
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
        Set<ItemCreationOrderRequest> items,
        @NotNull(message = "orderDate cannot be null")
        Instant orderDate,
        @NotNull(message = "currency cannot be null")
        String currency
) {

    public CreateOrderCommand toCommand() {
        final var commandItems = items().stream()
                .map(itemCreationOrderRequest -> new ItemCreateOrderCommand(itemCreationOrderRequest.quantity(), itemCreationOrderRequest.itemId()))
                .collect(Collectors.toSet());

        return new CreateOrderCommand(sellerId(), customerId(), commandItems, orderDate(), currency());
    }
}


