package com.example.market_web.orders.dto.response;

import com.example.market_web.orders.dto.OrderDetailDTO;
import com.example.market_web.orders.dto.UserDTO;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateOrderResponseDTO {
    private Integer id;
    private LocalDateTime orderDate;
    private BigDecimal total;
    private UserDTO user;
    private List<OrderDetailDTO> orderDetails;
}
