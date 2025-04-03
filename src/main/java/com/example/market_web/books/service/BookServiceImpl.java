package com.example.market_web.books.service;

import com.example.market_web.books.entity.BookEntity;
import com.example.market_web.commons.utilities.Utilities;
import com.example.market_web.books.dto.response.GetAvailableBookResponseDTO;
import com.example.market_web.books.mapper.BookMapper;
import com.example.market_web.books.repository.BookRepository;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.logging.Level;

@Service
public class BookServiceImpl implements BookService {

    private static final java.util.logging.Logger log = java.util.logging.Logger.getLogger(String.valueOf(BookServiceImpl.class));

    private final BookRepository bookRepository;

    private final BookMapper bookMapper;

    private final Utilities utilities;

    public BookServiceImpl(BookRepository bookRepository, BookMapper bookMapper, Utilities utilities) {
        this.bookRepository = bookRepository;
        this.bookMapper = bookMapper;
        this.utilities = utilities;
    }

    @Override
    @Cacheable(value = "GetAvailableBookResponseDTO", key = "#stock")
    public Page<GetAvailableBookResponseDTO> getAvailableBooks(Integer page, Integer pageSize, Integer stock, String field, Boolean isAsc) {
        log.log(Level.INFO, "A new book search request has been initiated with the following data, page {0} - page {1} - page {2} - page {3} - page {4}", new Object[]{page, pageSize, stock, field, isAsc});
        Sort sort = utilities.buildSort(field, isAsc);
        return bookMapper.entityToDto(bookRepository.findByStockGreaterThanEqual(stock, PageRequest.of(page, pageSize, sort)));
    }

    @Override
    public BookEntity discountStockById(Integer bookId, Integer quantity) {
        log.log(Level.INFO, "A new discount stock by bookId request has been initiated with the following data. bookId: {0} - quantity: {1}", new Object[]{bookId, quantity});

        BookEntity bookEntity = bookRepository.findById(bookId)
                .orElseThrow(() -> new RuntimeException("The book with bookId '"+ bookId + "' was not found"));

        if (bookEntity.getStock() < quantity) {
            throw new RuntimeException("Insufficient stock for the book: " + bookEntity.getTitle() + " - bookId: "+bookEntity.getId());
        }
        bookEntity.setStock(bookEntity.getStock() - quantity);
        return bookRepository.save(bookEntity);
    }
}
