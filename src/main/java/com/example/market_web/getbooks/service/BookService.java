package com.example.market_web.getbooks.service;

import com.example.market_web.getbooks.dto.BookDTO;
import org.springframework.data.domain.Page;

public interface BookService {
    Page<BookDTO> getAvailableBooks(Integer page, Integer pageSize, Integer stock, String field, Boolean isAsc);
}
