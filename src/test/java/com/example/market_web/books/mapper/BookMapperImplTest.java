package com.example.market_web.books.mapper;

import com.example.market_web.books.dto.response.PatchBookResponseDTO;
import com.example.market_web.books.entity.BookEntity;
import com.example.market_web.books.dto.response.GetAvailableBookResponseDTO;
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
        BookEntity bookEntity = BookEntity.builder()
                .id(id)
                .title(title)
                .build();
        Page<BookEntity> entityPage = new PageImpl<>(List.of(bookEntity));
        GetAvailableBookResponseDTO expectedResponse = GetAvailableBookResponseDTO.builder()
                .id(id)
                .title(title)
                .build();

        when(modelMapper.map(bookEntity, GetAvailableBookResponseDTO.class)).thenReturn(expectedResponse);

        assertEquals(expectedResponse, bookMapper.entityToDto(entityPage).getContent().getFirst());
    }
    @Test
    @DisplayName("Should return a PatchBookResponseDTO in method entityToDto")
    public void returnPatchBookResponseDTOInEntityToDto() {
        Integer id = 1941;
        String title = "title";
        BookEntity bookEntity = BookEntity.builder()
                .id(id)
                .title(title)
                .build();

        PatchBookResponseDTO expectedResponse = PatchBookResponseDTO.builder().id(1941).title(title).build();

        when(modelMapper.map(bookEntity, PatchBookResponseDTO.class)).thenReturn(expectedResponse);
        assertEquals(expectedResponse, bookMapper.entityToDto(bookEntity));
    }
}
