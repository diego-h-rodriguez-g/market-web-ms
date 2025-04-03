package com.example.market_web.orders.service;

import com.example.market_web.orders.dto.request.CreateOrderRequestDTO;
import com.example.market_web.orders.dto.response.CreateOrderResponseDTO;

public interface OrderService {
    CreateOrderResponseDTO createOrder(CreateOrderRequestDTO createOrderRequestDTO);
}
