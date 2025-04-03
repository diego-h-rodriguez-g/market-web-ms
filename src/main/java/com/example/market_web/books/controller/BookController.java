package com.example.market_web.books.controller;

import com.example.market_web.books.dto.request.PatchBookRequestDTO;
import com.example.market_web.books.dto.response.GetAvailableBookResponseDTO;
import com.example.market_web.books.dto.response.PatchBookResponseDTO;
import com.example.market_web.books.service.BookService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping(path = "/books/search", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Page<GetAvailableBookResponseDTO>> getAvailableBooks(
            @RequestParam Integer page,
            @RequestParam Integer pageSize,
            @RequestParam Integer stock,
            @RequestParam String field,
            @RequestParam Boolean isAsc) {
        return ResponseEntity.ok(bookService.getAvailableBooks(page, pageSize, stock, field, isAsc));
    }

    @PatchMapping(path = "/books/{bookId}",consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PatchBookResponseDTO> patchBooks(
            @PathVariable Integer bookId,
            @RequestBody @Valid PatchBookRequestDTO patchBookRequestDTO) {
        return ResponseEntity.ok(bookService.patchBook(bookId, patchBookRequestDTO));
    }
}
