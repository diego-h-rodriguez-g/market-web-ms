package com.example.market_web.orders.service;

import com.example.market_web.books.entity.BookEntity;
import com.example.market_web.books.service.BookService;
import com.example.market_web.commons.entity.OrderDetailEntity;
import com.example.market_web.commons.entity.OrderEntity;
import com.example.market_web.users.entity.UserEntity;
import com.example.market_web.commons.utilities.Utilities;
import com.example.market_web.orders.dto.request.CreateOrderRequestDTO;
import com.example.market_web.orders.dto.response.CreateOrderResponseDTO;
import com.example.market_web.orders.mapper.OrderMapper;
import com.example.market_web.orders.repository.OrderRepository;
import com.example.market_web.users.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.logging.Level;

@Service
public class OrderServiceImpl implements OrderService {

    private static final java.util.logging.Logger log = java.util.logging.Logger.getLogger(String.valueOf(OrderServiceImpl.class));

    private final OrderRepository orderRepository;
    private final BookService bookService;
    private final UserService userService;
    private final OrderMapper orderMapper;
    private final Utilities utilities;

    public OrderServiceImpl(OrderRepository orderRepository, BookService bookService, UserService userService, OrderMapper orderMapper, Utilities utilities) {
        this.orderRepository = orderRepository;
        this.bookService = bookService;
        this.userService = userService;
        this.orderMapper = orderMapper;
        this.utilities = utilities;
    }

    @Transactional
    @Override
    public CreateOrderResponseDTO createOrder(CreateOrderRequestDTO createOrderRequestDTO) {
        log.log(Level.INFO, "A new create order request has been initiated with the following data: {0}", new Object[]{utilities.convertObjectToString(createOrderRequestDTO)});
        UserEntity userEntity = userService.findUserById(createOrderRequestDTO.getUserId());

        List<OrderDetailEntity> orderDetails = createOrderRequestDTO.getItems().stream().map(item -> {
            BookEntity bookEntity = bookService.discountStockById(item.getBookId(), item.getQuantity());
            return OrderDetailEntity.builder().book(bookEntity).quantity(item.getQuantity()).unitPrice(bookEntity.getPrice()).build();
        }).toList();

        BigDecimal total = orderDetails.stream()
                .map(detail -> detail.getUnitPrice().multiply(BigDecimal.valueOf(detail.getQuantity())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        OrderEntity orderEntity = OrderEntity.builder()
                .orderDate(LocalDateTime.now())
                .total(total)
                .user(userEntity)
                .orderDetails(orderDetails)
                .build();

        orderDetails.forEach(productEntity -> productEntity.setOrder(orderEntity));
        OrderEntity orderEntityResponse = orderRepository.save(orderEntity);

        log.log(Level.INFO, "A new create order response has been ended with the following data: {0}", new Object[]{utilities.convertObjectToString(orderEntityResponse)});
        return orderMapper.entityToDto(orderEntityResponse);
    }
}
