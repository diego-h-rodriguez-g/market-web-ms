package com.example.market_web.books.mapper;

import com.example.market_web.books.entity.BookEntity;
import com.example.market_web.books.dto.response.GetAvailableBookResponseDTO;
import org.springframework.data.domain.Page;

public interface BookMapper {
    Page<GetAvailableBookResponseDTO> entityToDto(Page<BookEntity> bookEntities);
}
