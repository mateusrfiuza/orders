package com.sample.fixture;

import com.sample.domain.orders.usecase.commands.CreateOrderCommand;
import com.sample.domain.orders.usecase.commands.ItemCreateOrderCommand;

import java.time.Instant;
import java.util.Set;
import java.util.UUID;

public class CreateOrderCommandFixture {

    public static CreateOrderCommand gimmeValidCreateOrderCommand() {
        return new CreateOrderCommand(
                UUID.randomUUID().toString(),
                UUID.randomUUID().toString(),
                Set.of(new ItemCreateOrderCommand(1L, UUID.randomUUID().toString())),
                Instant.now(),
                "BRL"
        );
    }
}
