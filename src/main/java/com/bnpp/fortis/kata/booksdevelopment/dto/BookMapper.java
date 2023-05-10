package com.bnpp.fortis.kata.booksdevelopment.dto;

import com.bnpp.fortis.kata.booksdevelopment.storerepository.BookDevelopmentStackDetails;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface BookMapper {


    Book mapper(BookDevelopmentStackDetails bookDevelopmentStackDetails);

}

