package com.example.market_web.books.service;

import com.example.market_web.books.dto.request.PatchBookRequestDTO;
import com.example.market_web.books.dto.response.PatchBookResponseDTO;
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

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
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
    private Page<GetAvailableBookResponseDTO> pageExpectedResponse;

    private static final String TITLE = "title";

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
        when(bookMapper.entityToDto(entityPage)).thenReturn(pageExpectedResponse);

        assertEquals(pageExpectedResponse, bookService.getAvailableBooks(page, pageSize, stock, field, isAsc));
    }

    @Test
    @DisplayName("Should return a BookEntity in method discountStockById")
    void returnBookEntityInDiscountStockById() {
        Integer bookId = 1;
        Integer quantity = 2;
        Integer initialStock = 5;
        Integer finalStock = initialStock - quantity;

        BookEntity bookEntity = new BookEntity();
        bookEntity.setId(bookId);
        bookEntity.setTitle(TITLE);
        bookEntity.setStock(initialStock);

        BookEntity expectedResponse = new BookEntity();
        expectedResponse.setId(bookId);
        expectedResponse.setTitle(TITLE);
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

        BookEntity bookEntity = new BookEntity();
        bookEntity.setId(bookId);
        bookEntity.setTitle(TITLE);
        bookEntity.setStock(initialStock);

        when(bookRepository.findById(bookId)).thenReturn(Optional.of(bookEntity));

        assertThrows(RuntimeException.class, () -> bookService.discountStockById(bookId, quantity));
    }

    @Test
    @DisplayName("Should return a PatchBookResponseDTO in method patchBook When newPrice Null")
    void returnPatchBookResponseDTOInPatchBookWhenNewPriceNull() {
        Integer bookId = 1;
        Integer additionalStock = 10;
        BigDecimal newPrice = BigDecimal.valueOf(19.41);
        Integer actualStock = 9;
        PatchBookRequestDTO requestDTO = PatchBookRequestDTO.builder()
                .additionalStock(additionalStock)
                .newPrice(newPrice)
                .build();

        BookEntity bookEntity = BookEntity.builder()
                .id(bookId)
                .title(TITLE)
                .price(newPrice)
                .stock(actualStock)
                .build();

        BookEntity updatedBook = new BookEntity();
        updatedBook.setId(bookId);
        updatedBook.setPrice(newPrice);
        updatedBook.setStock(10);

        PatchBookResponseDTO expectedResponse = PatchBookResponseDTO.builder()
                .id(bookId)
                .title(TITLE)
                .stock(additionalStock+actualStock)
                .price(newPrice)
                .build();

        when(bookRepository.findById(bookId)).thenReturn(Optional.of(bookEntity));
        when(utilities.objectIsNull(any())).thenReturn(false, true);
        when(bookRepository.save(bookEntity)).thenReturn(updatedBook);
        when(bookMapper.entityToDto(updatedBook)).thenReturn(expectedResponse);

        assertEquals(expectedResponse, bookService.patchBook(bookId, requestDTO));
    }

    @Test
    @DisplayName("Should return a PatchBookResponseDTO in method patchBook When additionalStock null ")
    void returnPatchBookResponseDTOInPatchBookWhenAdditionalStockNull() {
        Integer bookId = 1;
        Integer additionalStock = 10;
        BigDecimal price = BigDecimal.valueOf(19.41);
        Integer actualStock = 9;
        PatchBookRequestDTO requestDTO = PatchBookRequestDTO.builder()
                .additionalStock(additionalStock)
                .newPrice(price)
                .build();

        BookEntity bookEntity = BookEntity.builder()
                .id(bookId)
                .title(TITLE)
                .price(price)
                .stock(actualStock)
                .build();

        BookEntity updatedBook = new BookEntity();
        updatedBook.setId(bookId);
        updatedBook.setPrice(price);
        updatedBook.setStock(10);

        PatchBookResponseDTO expectedResponse = PatchBookResponseDTO.builder()
                .id(bookId)
                .title(TITLE)
                .stock(additionalStock+actualStock)
                .price(price)
                .build();

        when(bookRepository.findById(bookId)).thenReturn(Optional.of(bookEntity));
        when(utilities.objectIsNull(any())).thenReturn(true, false);
        when(bookRepository.save(bookEntity)).thenReturn(updatedBook);
        when(bookMapper.entityToDto(updatedBook)).thenReturn(expectedResponse);

        assertEquals(expectedResponse, bookService.patchBook(bookId, requestDTO));
    }

    @Test
    @DisplayName("Should throw an Exception in method patchBook when bookId not found")
    void throwExceptionInMethodPatchBookWhenBookIdNotFound() {
        Integer bookId = 999;
        Integer additionalStock = 10;
        BigDecimal price = BigDecimal.valueOf(19.41);
        PatchBookRequestDTO requestDTO = PatchBookRequestDTO.builder()
                .additionalStock(additionalStock)
                .newPrice(price)
                .build();

        when(bookRepository.findById(bookId)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> bookService.patchBook(bookId, requestDTO));
    }
}



