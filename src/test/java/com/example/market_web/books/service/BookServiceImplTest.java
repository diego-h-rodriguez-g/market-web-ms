package com.example.market_web.books.service;

import com.example.market_web.books.entity.BookEntity;
import com.example.market_web.commons.utilities.Utilities;
import com.example.market_web.books.dto.response.GetAvailableBookResponseDTO;
import com.example.market_web.books.mapper.BookMapper;
import com.example.market_web.books.repository.BookRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

public class BookServiceImplTest {
    @InjectMocks
    private BookServiceImpl bookService;

    @Mock
    private BookRepository bookRepository;

    @Mock
    private BookMapper bookMapper;

    @Mock
    private Utilities utilities;

    @Mock
    private Sort sort;

    @Mock
    private Page<BookEntity> entityPage;

    @Mock
    private Page<GetAvailableBookResponseDTO> expectedResponse;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Should return a Page BookDTO in method getAvailableBooks")
    public void returnPageBookDTOInGetAvailableBooks() {
        int page = 0;
        int pageSize = 10;
        Integer stock = 5;
        String field = "field";
        Boolean isAsc = true;
        PageRequest pageRequest = PageRequest.of(page, pageSize, sort);

        when(utilities.buildSort(field, isAsc)).thenReturn(sort);
        when(bookRepository.findByStockGreaterThanEqual(stock, pageRequest)).thenReturn(entityPage);
        when(bookMapper.entityToDto(entityPage)).thenReturn(expectedResponse);

        assertEquals(expectedResponse, bookService.getAvailableBooks(page, pageSize, stock, field, isAsc));
    }

    @Test
    @DisplayName("Should return a BookEntity in method discountStockById")
    void returnBookEntityInDiscountStockById() {
        Integer bookId = 1;
        Integer quantity = 2;
        Integer initialStock = 5;
        Integer finalStock = initialStock - quantity;
        String title = "title";

        BookEntity bookEntity = new BookEntity();
        bookEntity.setId(bookId);
        bookEntity.setTitle(title);
        bookEntity.setStock(initialStock);

        BookEntity expectedResponse = new BookEntity();
        expectedResponse.setId(bookId);
        expectedResponse.setTitle(title);
        expectedResponse.setStock(finalStock);

        when(bookRepository.findById(bookId)).thenReturn(Optional.of(bookEntity));
        when(bookRepository.save(bookEntity)).thenReturn(expectedResponse);

        BookEntity receivedResponse = bookService.discountStockById(bookId, quantity);

        assertEquals(expectedResponse, receivedResponse);
    }

    @Test
    @DisplayName("Should throw an Exception in method discountStockById when bookId not found")
    void throwExceptionInMethodDiscountStockByIdWhenBookIdNotFound() {
        Integer bookId = 999;
        Integer quantity = 1;

        when(bookRepository.findById(bookId)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> bookService.discountStockById(bookId, quantity));
    }

    @Test
    @DisplayName("Should throw an Exception in method discountStockById when insufficient stock")
    void throwExceptionInMethodDiscountStockByIdWhenInsufficientStock() {
        Integer bookId = 999;
        Integer quantity = 5;
        Integer initialStock = 1;
        String title = "title";

        BookEntity bookEntity = new BookEntity();
        bookEntity.setId(bookId);
        bookEntity.setTitle(title);
        bookEntity.setStock(initialStock);

        when(bookRepository.findById(bookId)).thenReturn(Optional.of(bookEntity));

        assertThrows(RuntimeException.class, () -> bookService.discountStockById(bookId, quantity));
    }
}



