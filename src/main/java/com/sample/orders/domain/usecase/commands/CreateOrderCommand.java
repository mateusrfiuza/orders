package com.sample.orders.domain.usecase.commands;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.time.Instant;
import java.util.Set;

public record CreateOrderCommand(
        @NotEmpty(message = "sellerId cannot be null")
        String sellerId,
        @NotEmpty(message = "customer cannot be null")
        String customerId,
        @NotEmpty(message = "items should have at least one item")
        Set<ItemCreateOrderCommand> items,
        @NotNull(message = "orderDate cannot be null")
        Instant orderDate,
        @NotNull(message = "currency cannot be null")
        String currency
) {
}


