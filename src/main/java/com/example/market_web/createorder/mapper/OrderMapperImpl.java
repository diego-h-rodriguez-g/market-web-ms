package com.example.market_web.createorder.mapper;

import com.example.market_web.commons.entity.OrderEntity;
import com.example.market_web.createorder.dto.OrderDetailDTO;
import com.example.market_web.createorder.dto.response.CreateOrderResponseDTO;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class OrderMapperImpl implements OrderMapper {

    private final ModelMapper modelMapper;

    public OrderMapperImpl(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public CreateOrderResponseDTO entityToDto(OrderEntity orderEntity) {

        return modelMapper.map(orderEntity, CreateOrderResponseDTO.class);
    }
}
