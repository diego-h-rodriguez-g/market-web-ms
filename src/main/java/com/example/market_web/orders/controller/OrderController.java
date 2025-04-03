package com.example.market_web.orders.controller;

import com.example.market_web.orders.dto.request.CreateOrderRequestDTO;
import com.example.market_web.orders.dto.response.CreateOrderResponseDTO;
import com.example.market_web.orders.service.OrderService;
import jakarta.validation.Valid;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping(path = "/orders/order", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CreateOrderResponseDTO> createOrder(
            @RequestBody @Valid CreateOrderRequestDTO createOrderRequestDTO) {
        return ResponseEntity.ok(orderService.createOrder(createOrderRequestDTO));
    }
}
