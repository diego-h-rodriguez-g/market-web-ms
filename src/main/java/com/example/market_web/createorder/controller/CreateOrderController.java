package com.example.market_web.createorder.controller;

import com.example.market_web.createorder.dto.request.CreateOrderRequestDTO;
import com.example.market_web.createorder.dto.response.CreateOrderResponseDTO;
import com.example.market_web.createorder.service.OrderService;
import jakarta.validation.Valid;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class CreateOrderController {

    private final OrderService orderService;

    public CreateOrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping(path = "/orders/order", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CreateOrderResponseDTO> createOrder(
            @RequestBody @Valid CreateOrderRequestDTO createOrderRequestDTO) {
        return ResponseEntity.ok(orderService.createOrder(createOrderRequestDTO));
    }
}
