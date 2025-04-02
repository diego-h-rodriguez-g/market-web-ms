package com.example.market_web.createorder.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderDTO {
    private Integer id;
    private LocalDateTime orderDate;
    private BigDecimal total;
    private UserDTO user;
    private List<OrderDetailDTO> orderDetails;
}
