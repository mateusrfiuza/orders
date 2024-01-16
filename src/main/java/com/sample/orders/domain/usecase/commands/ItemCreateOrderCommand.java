package com.sample.orders.domain.usecase.commands;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;

public record ItemCreateOrderCommand(
        @Min(value = 1, message = "quantity should be at least one")
        Long quantity,
        @NotEmpty(message = "itemId cannot be null")
        String itemId
) {
}
