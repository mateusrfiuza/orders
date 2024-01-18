package com.sample.entrypoint.http;

import com.sample.domain.orders.usecase.CreateOrderUseCase;
import com.sample.entrypoint.http.payload.CreationOrderRequest;
import com.sample.entrypoint.http.payload.CreationOrderResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

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
        @Operation(description = "Create a new Order")
        @ApiResponses(value = {
                @ApiResponse(responseCode = "202", description = "Order creation accepted"),
                @ApiResponse(responseCode = "400", description = "Your parameters are invalid"),
                @ApiResponse(responseCode = "500", description = "Some internal error happened"),
        })
    @ResponseStatus(value = HttpStatus.OK)
    public ResponseEntity<CreationOrderResponse> createOrder(@Valid @RequestBody final CreationOrderRequest request) {
        var orderId =  createOrderUseCase.execute(request.toCommand());
        return ResponseEntity
                .accepted()
                .body(new CreationOrderResponse(orderId));
    }


}
