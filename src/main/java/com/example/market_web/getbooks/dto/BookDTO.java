package com.example.market_web.getbooks.dto;

import lombok.*;
import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BookDTO {
    private Integer id;
    private String title;
    private Integer stock;
    private AuthorDTO author;
    private CategoryDTO category;
    private BigDecimal price;
}
