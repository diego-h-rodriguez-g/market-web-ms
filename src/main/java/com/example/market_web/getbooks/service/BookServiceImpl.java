package com.example.market_web.getbooks.service;

import com.example.market_web.commons.entity.BookEntity;
import com.example.market_web.commons.utilities.Utilities;
import com.example.market_web.getbooks.repository.BookRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.logging.Level;

@Service
public class BookServiceImpl implements BookService {

    private static final java.util.logging.Logger log = java.util.logging.Logger.getLogger(String.valueOf(BookServiceImpl.class));

    private final BookRepository bookRepository;

    private final Utilities utilities;

    public BookServiceImpl(BookRepository bookRepository, Utilities utilities) {
        this.bookRepository = bookRepository;
        this.utilities = utilities;
    }

    @Override
    public Page<BookEntity> getAvailableBooks(Integer page, Integer pageSize, Integer stock, String field, Boolean isAsc) {
        log.log(Level.INFO, "A new book search request has been initiated with the following data, page {0} - page {1} - page {2} - page {3} - page {4}", new Object[]{page, pageSize, stock, field, isAsc});
        Sort sort =  utilities.buildSort(field, isAsc);
        return bookRepository.findByStockGreaterThanEqual(stock, PageRequest.of(page, pageSize, sort));
    }
}
