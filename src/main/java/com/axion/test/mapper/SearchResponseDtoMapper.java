package com.axion.test.mapper;

import com.axion.test.model.client.HandsetClientResponseDto;
import com.axion.test.model.dto.SearchResponseDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SearchResponseDtoMapper {
    @Autowired
    ModelMapper modelMapper;

    //can be static method
    public SearchResponseDto mapToDto(HandsetClientResponseDto handsetClientResponseDto) {
        SearchResponseDto searchResponseDto = modelMapper.map(handsetClientResponseDto, SearchResponseDto.class);
        return searchResponseDto;
    }

}
