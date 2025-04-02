package com.example.market_web.createorder.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderDetailDTO {
    private Integer id;
    private Integer quantity;
    private BigDecimal unitPrice;
    @JsonIgnore
    private OrderDTO order;
    private BookDTO book;
}
