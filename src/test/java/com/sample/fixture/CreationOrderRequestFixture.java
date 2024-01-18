package com.sample.fixture;

import com.sample.entrypoint.http.payload.CreationOrderRequest;
import com.sample.entrypoint.http.payload.ItemCreationOrderRequest;

import java.time.Instant;
import java.util.HashSet;

public class CreationOrderRequestFixture {


    public static CreationOrderRequest gimmeCreationOrderRequest() {
        final var item = new ItemCreationOrderRequest(2L, "item1");
        var items = new HashSet<ItemCreationOrderRequest>();
        items.add(item);

        return new CreationOrderRequest(
                "sampleSellerId",
                "sampleCustomerId",
                items,
                Instant.now(),
                "USD"
        );
    }

    public static CreationOrderRequest gimmeCreationOrderRequestWithoutItems() {
        var items = new HashSet<ItemCreationOrderRequest>();

        return new CreationOrderRequest(
                "sampleSellerId",
                "sampleCustomerId",
                items,
                Instant.now(),
                "USD"
        );
    }

}
