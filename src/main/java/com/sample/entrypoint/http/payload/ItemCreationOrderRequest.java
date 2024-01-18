package com.sample.entrypoint.http.payload;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;

public record ItemCreationOrderRequest(
        @Min(value = 1, message = "quantity should be at least one")
        Long quantity,
        @NotEmpty(message = "itemId cannot be null")
        String itemId
) {
}
