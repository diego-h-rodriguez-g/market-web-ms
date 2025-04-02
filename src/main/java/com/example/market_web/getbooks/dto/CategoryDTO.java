package com.example.market_web.getbooks.dto;

import com.example.market_web.commons.entity.BookEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CategoryDTO {

    private Integer id;
    private String name;

    @ToString.Exclude
    @JsonIgnore
    private List<BookEntity> books;
}
