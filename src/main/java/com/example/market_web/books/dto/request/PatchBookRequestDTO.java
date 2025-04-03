package com.example.market_web.books.dto.request;

import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PatchBookRequestDTO {
    @Positive
    private BigDecimal newPrice;
    @PositiveOrZero
    private Integer additionalStock;
}
