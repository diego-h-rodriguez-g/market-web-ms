package com.example.market_web.createorder.service;

import com.example.market_web.commons.entity.BookEntity;
import com.example.market_web.commons.entity.OrderDetailEntity;
import com.example.market_web.commons.entity.OrderEntity;
import com.example.market_web.commons.entity.UserEntity;
import com.example.market_web.commons.repository.UserRepository;
import com.example.market_web.commons.utilities.Utilities;
import com.example.market_web.createorder.dto.request.CreateOrderRequestDTO;
import com.example.market_web.createorder.dto.response.CreateOrderResponseDTO;
import com.example.market_web.createorder.mapper.OrderMapper;
import com.example.market_web.createorder.repository.OrderRepository;
import com.example.market_web.getbooks.repository.BookRepository;
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
    private final BookRepository bookRepository;
    private final UserRepository userRepository;
    private final OrderMapper orderMapper;
    private final Utilities utilities;

    public OrderServiceImpl(OrderRepository orderRepository, BookRepository bookRepository, UserRepository userRepository, OrderMapper orderMapper, Utilities utilities) {
        this.orderRepository = orderRepository;
        this.bookRepository = bookRepository;
        this.userRepository = userRepository;
        this.orderMapper = orderMapper;
        this.utilities = utilities;
    }

    @Transactional
    @Override
    public CreateOrderResponseDTO createOrder(CreateOrderRequestDTO createOrderRequestDTO) {
        log.log(Level.INFO, "A new create order request has been initiated with the following data: {0}", new Object[]{utilities.convertObjectToString(createOrderRequestDTO)});

        UserEntity user = userRepository.findById(createOrderRequestDTO.getUserId())
                .orElseThrow(() -> new RuntimeException("User does not exist"));

        List<OrderDetailEntity> orderDetails = createOrderRequestDTO.getItems().stream().map(item -> {
            BookEntity bookEntity = bookRepository.findById(item.getBookId())
                    .orElseThrow(() -> new RuntimeException("Libro no encontrado"));

            if (bookEntity.getStock() < item.getQuantity()) {
                throw new RuntimeException("Stock insuficiente para " + bookEntity.getTitle());
            }
            bookEntity.setStock(bookEntity.getStock() - item.getQuantity());
            bookRepository.save(bookEntity);

            return OrderDetailEntity.builder().book(bookEntity).quantity(item.getQuantity()).unitPrice(bookEntity.getPrice()).build();
        }).toList();


        BigDecimal total = orderDetails.stream()
                .map(detail -> detail.getUnitPrice().multiply(BigDecimal.valueOf(detail.getQuantity())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        OrderEntity orderEntity = new OrderEntity();
        orderEntity.setOrderDate(LocalDateTime.now());
        orderEntity.setTotal(total);
        orderEntity.setUser(user);
        orderEntity.setOrderDetails(orderDetails);

        orderDetails.forEach(productEntity -> productEntity.setOrder(orderEntity));
        OrderEntity orderEntityResponse = orderRepository.save(orderEntity);

        log.log(Level.INFO, "A new create order response has been ended with the following data: {0}", new Object[]{utilities.convertObjectToString(orderEntityResponse)});

        return orderMapper.entityToDto(orderEntityResponse);
    }
}
