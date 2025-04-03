package com.example.market_web.orders.controller;

import com.example.market_web.orders.dto.request.CreateOrderRequestDTO;
import com.example.market_web.orders.dto.response.CreateOrderResponseDTO;
import com.example.market_web.orders.service.OrderService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class OrderControllerTest {
    @InjectMocks
    private OrderController orderController;

    @Mock
    private OrderService orderService;


    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Should return a CreateOrderResponseDTO response in method createOrder")
    public void returnCreateOrderResponseDTOInCreateOrder() {
        CreateOrderRequestDTO createOrderRequestDTO = CreateOrderRequestDTO.builder().build();
        CreateOrderResponseDTO createOrderResponseDTO = CreateOrderResponseDTO.builder().build();
        ResponseEntity<CreateOrderResponseDTO> expectedResponse = ResponseEntity.ok(createOrderResponseDTO);

        when(orderService.createOrder(createOrderRequestDTO)).thenReturn(createOrderResponseDTO);

        ResponseEntity<CreateOrderResponseDTO> receivedResponse = orderController.createOrder(createOrderRequestDTO);
        assertEquals(expectedResponse.getBody(), receivedResponse.getBody());
    }
}
