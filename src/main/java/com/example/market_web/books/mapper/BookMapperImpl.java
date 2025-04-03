package com.example.market_web.books.mapper;

import com.example.market_web.books.entity.BookEntity;
import com.example.market_web.books.dto.response.GetAvailableBookResponseDTO;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

@Component
public class BookMapperImpl implements BookMapper{
    private final ModelMapper modelMapper;

    public BookMapperImpl(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public Page<GetAvailableBookResponseDTO> entityToDto(Page<BookEntity> bookEntities){
        return bookEntities.map(objectEntity -> modelMapper.map(objectEntity, GetAvailableBookResponseDTO.class));
    }
}
