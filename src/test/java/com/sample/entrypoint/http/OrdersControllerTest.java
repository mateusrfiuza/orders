package com.sample.entrypoint.http;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sample.domain.orders.usecase.CreateOrderUseCase;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
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

@SpringBootTest
@AutoConfigureMockMvc
class OrdersControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private CreateOrderUseCase createOrderUseCase;


    @Test
    void should_return_201_when_receive_a_valid_request_for_order_creation() throws Exception {

        // Given
        when(createOrderUseCase.execute(any())).thenReturn("1");
        var request = gimmeCreationOrderRequest();

        // When
        var result = mockMvc.perform(post("/orders")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)));

        // Then
        result.andExpect(status().isAccepted())
                .andExpect(jsonPath("$.orderId").value("1"))
                .andReturn();
    }

    @Test
    void should_return_400_when_receive_an_invalid_payload_for_order_creation() throws Exception {

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