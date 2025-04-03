package com.example.market_web.books.service;

import com.example.market_web.books.dto.response.GetAvailableBookResponseDTO;
import com.example.market_web.books.entity.BookEntity;
import org.springframework.data.domain.Page;

public interface BookService {
    Page<GetAvailableBookResponseDTO> getAvailableBooks(Integer page, Integer pageSize, Integer stock, String field, Boolean isAsc);

    BookEntity discountStockById(Integer bookId, Integer quantity);

}
