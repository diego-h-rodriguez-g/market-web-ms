package com.example.market_web.getbooks.mapper;

import com.example.market_web.commons.entity.BookEntity;
import com.example.market_web.getbooks.dto.BookDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class BookMapperImplTest {

    @InjectMocks
    private BookMapperImpl bookMapper;

    @Mock
    private ModelMapper modelMapper;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Should return a Page BookDTO response in method entityToDto")
    public void returnPageBookDTOInEntityToDto() {
        Integer id = 1941;
        String title = "title";
        BookEntity bookEntity = new BookEntity();
        bookEntity.setId(id);
        bookEntity.setTitle(title);
        Page<BookEntity> entityPage = new PageImpl<>(List.of(bookEntity));
        BookDTO expectedResponse = new BookDTO();
        expectedResponse.setId(1941);
        expectedResponse.setTitle(title);

        when(modelMapper.map(bookEntity, BookDTO.class)).thenReturn(expectedResponse);

        Page<BookDTO> receivedResponse = bookMapper.entityToDto(entityPage);
        assertEquals(expectedResponse, receivedResponse.getContent().getFirst());
    }
}
