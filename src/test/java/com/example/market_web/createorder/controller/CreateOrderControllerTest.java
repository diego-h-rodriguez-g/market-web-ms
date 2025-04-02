package com.example.market_web.createorder.controller;

import com.example.market_web.createorder.dto.request.CreateOrderRequestDTO;
import com.example.market_web.createorder.dto.response.CreateOrderResponseDTO;
import com.example.market_web.createorder.service.OrderService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class CreateOrderControllerTest {
    @InjectMocks
    private CreateOrderController createOrderController;

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

        ResponseEntity<CreateOrderResponseDTO> receivedResponse = createOrderController.createOrder(createOrderRequestDTO);

        assertEquals(expectedResponse.getBody(), receivedResponse.getBody());
    }
}
