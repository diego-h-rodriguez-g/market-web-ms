package com.example.market_web.books.controller;

import com.example.market_web.books.dto.response.GetAvailableBookResponseDTO;
import com.example.market_web.books.service.BookService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

public class BookControllerTest {
    @InjectMocks
    private BookController bookController;

    @Mock
    private BookService bookService;

    @Mock
    private Page<GetAvailableBookResponseDTO> expectedResponse;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Should return a successful response in method getAvailableBooks")
    public void returnSuccessfulInGetAvailableBooks() {
        Integer page = 0;
        Integer pageSize = 10;
        Integer stock = 5;
        String field = "field";
        Boolean isAsc = true;

        when(bookService.getAvailableBooks(page, pageSize, stock, field, isAsc)).thenReturn(expectedResponse);

        ResponseEntity<Page<GetAvailableBookResponseDTO>> response = bookController.getAvailableBooks(page, pageSize, stock, field, isAsc);

        assertTrue(response.getStatusCode().is2xxSuccessful());
        assertEquals(expectedResponse, response.getBody());
    }
}
