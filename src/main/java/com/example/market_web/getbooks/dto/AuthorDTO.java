package com.example.market_web.getbooks.dto;

import com.example.market_web.commons.entity.BookEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthorDTO {

    private Integer id;

    private String firstName;

    private String lastName;

    private String nationality;

    @ToString.Exclude
    @JsonIgnore
    private List<BookEntity> books;
}
