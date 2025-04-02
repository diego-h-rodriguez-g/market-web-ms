package com.example.market_web.getbooks.mapper;

import com.example.market_web.commons.entity.BookEntity;
import com.example.market_web.getbooks.dto.BookDTO;
import org.springframework.data.domain.Page;

public interface BookMapper {
    Page<BookDTO> entityToDto(Page<BookEntity> bookEntities);
}
