package com.example.market_web.createorder.service;

import com.example.market_web.createorder.dto.request.CreateOrderRequestDTO;
import com.example.market_web.createorder.dto.response.CreateOrderResponseDTO;;

public interface OrderService {
    CreateOrderResponseDTO createOrder(CreateOrderRequestDTO createOrderRequestDTO);
}
