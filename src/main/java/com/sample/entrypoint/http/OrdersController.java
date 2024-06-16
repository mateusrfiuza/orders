package com.sample.entrypoint.http;

import com.sample.domain.orders.usecase.CreateOrderUseCase;
import com.sample.entrypoint.http.payload.CreationOrderRequest;
import com.sample.entrypoint.http.payload.CreationOrderResponse;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Validated
@RequestMapping("/orders")
public class OrdersController {

    private static final Logger logger = LoggerFactory.getLogger(OrdersController.class);

    private final CreateOrderUseCase createOrderUseCase;

    public OrdersController(final CreateOrderUseCase createOrderUseCase) {
        this.createOrderUseCase = createOrderUseCase;
    }

    @PostMapping
    public ResponseEntity<CreationOrderResponse> createOrder(@Valid @RequestBody final CreationOrderRequest request) {
        var orderId =  createOrderUseCase.execute(request.toCommand());
        return new ResponseEntity<>(new CreationOrderResponse(orderId), HttpStatus.CREATED);

    }


}
