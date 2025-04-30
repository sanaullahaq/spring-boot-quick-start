package com.sana.database.mappers.impl;

import com.sana.database.domain.dto.AuthorDto;
import com.sana.database.domain.entities.AuthorEntity;
import com.sana.database.mappers.Mapper;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class AuthorMapperImpl implements Mapper<AuthorEntity, AuthorDto> {
    private ModelMapper modelMapper;

    public AuthorMapperImpl(ModelMapper modelMapper){
        this.modelMapper=modelMapper;
    }

    @Override
    public AuthorDto mapTo(AuthorEntity authorEntity) {
        // entity -> dto
        return modelMapper.map(authorEntity, AuthorDto.class);
    }

    @Override
    public AuthorEntity mapFrom(AuthorDto authorDto) {
        // dto -> entity
        return modelMapper.map(authorDto, AuthorEntity.class);
    }
}
