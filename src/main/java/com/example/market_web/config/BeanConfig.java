package com.example.market_web.config;

import com.example.market_web.commons.entity.OrderEntity;
import com.example.market_web.createorder.dto.OrderDetailDTO;
import com.example.market_web.createorder.dto.response.CreateOrderResponseDTO;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class BeanConfig {

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();
        TypeMap<OrderEntity, CreateOrderResponseDTO> typeMap = modelMapper.createTypeMap(OrderEntity.class, CreateOrderResponseDTO.class);

        typeMap.addMappings(mapper -> mapper.using(ctx -> new ArrayList<>((List<OrderDetailDTO>) ctx.getSource()))
                .map(OrderEntity::getOrderDetails, CreateOrderResponseDTO::setOrderDetails));

        return modelMapper;
    }
}
