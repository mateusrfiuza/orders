package com.sample.entrypoint.http;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sample.domain.orders.usecase.CreateOrderUseCase;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static com.sample.fixture.CreationOrderRequestFixture.gimmeCreationOrderRequest;
import static com.sample.fixture.CreationOrderRequestFixture.gimmeCreationOrderRequestWithoutItems;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(OrdersController.class)
class OrdersControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private CreateOrderUseCase createOrderUseCase;


    @Test
    void shouldReturnCreatedStatusWhenValidOrderRequestIsProvided() throws Exception {

        // Given
        when(createOrderUseCase.execute(any())).thenReturn("1");
        var request = gimmeCreationOrderRequest();

        // When
        var result = mockMvc.perform(post("/orders")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)));

        // Then
        result.andExpect(status().isCreated())
                .andExpect(jsonPath("$.orderId").value("1"))
                .andReturn();
    }

    @Test
    void shouldReturnBadRequestStatusWhenOrderRequestWithoutItemsIsProvided() throws Exception {

        // Given
        var request = gimmeCreationOrderRequestWithoutItems();

        // When
        var result = mockMvc.perform(post("/orders")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)));

        // Then
        result.andExpect(status().isBadRequest()).andReturn();
    }

}