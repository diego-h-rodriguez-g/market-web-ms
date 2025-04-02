package com.example.market_web.createorder.mapper;

import com.example.market_web.commons.entity.OrderEntity;
import com.example.market_web.createorder.dto.response.CreateOrderResponseDTO;

public interface OrderMapper {
    CreateOrderResponseDTO entityToDto(OrderEntity orderEntity);
}
