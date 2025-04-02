package com.example.market_web.getbooks.service;

import com.example.market_web.commons.entity.BookEntity;
import com.example.market_web.commons.utilities.Utilities;
import com.example.market_web.getbooks.dto.BookDTO;
import com.example.market_web.getbooks.mapper.BookMapper;
import com.example.market_web.getbooks.repository.BookRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import static org.junit.jupiter.api.Assertions.assertEquals;
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
    private Page<BookDTO> expectedResponse;

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
}



