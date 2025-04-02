package com.example.market_web.createorder.dto.response;

import com.example.market_web.createorder.dto.OrderDetailDTO;
import com.example.market_web.createorder.dto.UserDTO;
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
