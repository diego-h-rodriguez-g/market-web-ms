package com.example.market_web.orders.dto.request;

import com.example.market_web.orders.dto.OrderItemDTO;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateOrderRequestDTO {
    @NotNull
    @Positive
    private Integer userId;

    @NotEmpty
    @Valid
    private List<OrderItemDTO> items;
}
