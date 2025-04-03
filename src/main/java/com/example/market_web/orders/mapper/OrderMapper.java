package com.example.market_web.orders.mapper;

import com.example.market_web.commons.entity.OrderEntity;
import com.example.market_web.orders.dto.response.CreateOrderResponseDTO;

public interface OrderMapper {
    CreateOrderResponseDTO entityToDto(OrderEntity orderEntity);
}
