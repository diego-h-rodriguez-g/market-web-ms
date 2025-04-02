package com.example.market_web.getbooks.mapper;

import com.example.market_web.commons.entity.BookEntity;
import com.example.market_web.getbooks.dto.BookDTO;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

@Component
public class BookMapperImpl implements BookMapper{
    private final ModelMapper modelMapper;

    public BookMapperImpl(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public Page<BookDTO> entityToDto(Page<BookEntity> bookEntities){
        return bookEntities.map(objectEntity -> modelMapper.map(objectEntity, BookDTO.class));
    }
}
