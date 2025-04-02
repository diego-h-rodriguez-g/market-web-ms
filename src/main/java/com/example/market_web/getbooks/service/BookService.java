package com.example.market_web.getbooks.service;

import com.example.market_web.commons.entity.BookEntity;
import org.springframework.data.domain.Page;

public interface BookService {
    Page<BookEntity> getAvailableBooks(Integer page, Integer pageSize, Integer stock, String field, Boolean isAsc);
}
