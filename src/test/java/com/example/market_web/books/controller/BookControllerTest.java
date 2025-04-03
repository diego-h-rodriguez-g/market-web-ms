package com.example.market_web.books.controller;

import com.example.market_web.books.dto.request.PatchBookRequestDTO;
import com.example.market_web.books.dto.response.GetAvailableBookResponseDTO;
import com.example.market_web.books.dto.response.PatchBookResponseDTO;
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
    private Page<GetAvailableBookResponseDTO> pageExpectedResponse;

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

        when(bookService.getAvailableBooks(page, pageSize, stock, field, isAsc)).thenReturn(pageExpectedResponse);

        ResponseEntity<Page<GetAvailableBookResponseDTO>> response = bookController.getAvailableBooks(page, pageSize, stock, field, isAsc);

        assertTrue(response.getStatusCode().is2xxSuccessful());
        assertEquals(pageExpectedResponse, response.getBody());
    }

    @Test
    @DisplayName("Should return a successful response in method patchBooks")
    public void returnSuccessfulInPatchBooks() {
        Integer bookId = 1;
        PatchBookRequestDTO patchBookRequestDTO = PatchBookRequestDTO.builder().build();
        PatchBookResponseDTO patchBookResponseDTO = PatchBookResponseDTO.builder().build();
        ResponseEntity<PatchBookResponseDTO> expectedResponse = ResponseEntity.ok(patchBookResponseDTO);

        when(bookService.patchBook(bookId, patchBookRequestDTO)).thenReturn(patchBookResponseDTO);

        ResponseEntity<PatchBookResponseDTO> receivedResponse = bookController.patchBooks(bookId, patchBookRequestDTO);

        assertTrue(receivedResponse.getStatusCode().is2xxSuccessful());
        assertEquals(expectedResponse, receivedResponse);
    }
}
