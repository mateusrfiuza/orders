package com.sample.domain.orders.usecase.commands;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;

public record ItemCreateOrderCommand(
        @Min(value = 1, message = "quantity should be at least one")
        Long quantity,
        @NotEmpty(message = "productId cannot be null")
        String productId
) {
}
