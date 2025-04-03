package com.example.market_web.orders.service;

import com.example.market_web.books.entity.BookEntity;
import com.example.market_web.books.service.BookService;
import com.example.market_web.commons.entity.OrderDetailEntity;
import com.example.market_web.commons.entity.OrderEntity;
import com.example.market_web.commons.utilities.Utilities;
import com.example.market_web.orders.dto.OrderItemDTO;
import com.example.market_web.orders.dto.request.CreateOrderRequestDTO;
import com.example.market_web.orders.dto.response.CreateOrderResponseDTO;
import com.example.market_web.orders.mapper.OrderMapper;
import com.example.market_web.orders.repository.OrderRepository;
import com.example.market_web.users.entity.UserEntity;
import com.example.market_web.users.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class OrderServiceImplTest {
    @InjectMocks
    private OrderServiceImpl orderService;
    @Mock
    private OrderRepository orderRepository;
    @Mock
    private BookService bookService;
    @Mock
    private UserService userService;
    @Mock
    private OrderMapper orderMapper;
    @Mock
    private Utilities utilities;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Should return a CreateOrderResponseDTO in method createOrder")
    public void returnCreateOrderResponseDTOInCreateOrder() {
        Integer userId = 1;
        Integer bookId = 1;
        int quantity = 2;
        BigDecimal price = BigDecimal.valueOf(40.40);

        CreateOrderRequestDTO createOrderRequestDTO = new CreateOrderRequestDTO();
        createOrderRequestDTO.setUserId(userId);
        createOrderRequestDTO.setItems(List.of(
                new OrderItemDTO(bookId, quantity)
        ));

        UserEntity userEntity = new UserEntity();
        userEntity.setId(userId);

        BookEntity bookEntity = new BookEntity();
        bookEntity.setId(bookId);
        bookEntity.setPrice(price);
        bookEntity.setStock(10);

        OrderDetailEntity orderDetail = OrderDetailEntity.builder()
                .book(bookEntity)
                .quantity(quantity)
                .unitPrice(price)
                .build();

        OrderEntity savedOrder = OrderEntity.builder()
                .orderDate(LocalDateTime.now())
                .total(price.multiply(BigDecimal.valueOf(quantity)))
                .user(userEntity)
                .orderDetails(List.of(orderDetail))
                .build();

        CreateOrderResponseDTO expectedResponseDTO = CreateOrderResponseDTO.builder()
                .id(userId)
                .orderDate(LocalDateTime.now())
                .total(price.multiply(BigDecimal.valueOf(quantity)))
                .build();

        expectedResponseDTO.setTotal(price.multiply(BigDecimal.valueOf(quantity)));

        when(userService.findUserById(createOrderRequestDTO.getUserId())).thenReturn(userEntity);
        when(bookService.discountStockById(bookId, quantity)).thenReturn(bookEntity);
        when(orderRepository.save(any(OrderEntity.class))).thenReturn(savedOrder);
        when(utilities.convertObjectToString(any())).thenReturn("{}");
        when(orderMapper.entityToDto(savedOrder)).thenReturn(expectedResponseDTO);

        assertEquals(expectedResponseDTO, orderService.createOrder(createOrderRequestDTO));
    }
}
