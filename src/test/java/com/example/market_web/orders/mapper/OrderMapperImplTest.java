package com.example.market_web.orders.mapper;

import com.example.market_web.commons.entity.OrderEntity;
import com.example.market_web.orders.dto.response.CreateOrderResponseDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class OrderMapperImplTest {

    @InjectMocks
    private OrderMapperImpl orderMapperImpl;

    @Mock
    private ModelMapper modelMapper;


    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Should return a Page BookDTO response in method entityToDto")
    public void returnPageBookDTOInEntityToDto() {
        OrderEntity orderEntity = OrderEntity.builder().build();
        CreateOrderResponseDTO expectedResponse = CreateOrderResponseDTO.builder().build();

        when(modelMapper.map(orderEntity, CreateOrderResponseDTO.class)).thenReturn(expectedResponse);

        assertEquals(expectedResponse, orderMapperImpl.entityToDto(orderEntity));
    }
}
