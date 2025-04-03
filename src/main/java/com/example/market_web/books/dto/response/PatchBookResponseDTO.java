package com.example.market_web.books.dto.response;

import com.example.market_web.books.dto.AuthorDTO;
import com.example.market_web.books.dto.CategoryDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PatchBookResponseDTO {
    private Integer id;
    private String title;
    private Integer stock;
    private AuthorDTO author;
    private CategoryDTO category;
    private BigDecimal price;
}
