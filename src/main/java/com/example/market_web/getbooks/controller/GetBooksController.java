package com.example.market_web.getbooks.controller;

import com.example.market_web.getbooks.dto.BookDTO;
import com.example.market_web.getbooks.service.BookService;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class GetBooksController {

    private final BookService bookService;

    public GetBooksController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping(path = "/books/search", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Page<BookDTO>> getAvailableBooks(
            @RequestParam Integer page,
            @RequestParam Integer pageSize,
            @RequestParam Integer stock,
            @RequestParam String field,
            @RequestParam Boolean isAsc) {
        return ResponseEntity.ok(bookService.getAvailableBooks(page, pageSize, stock, field, isAsc));
    }
}
