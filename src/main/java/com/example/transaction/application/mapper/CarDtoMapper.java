package com.example.transaction.application.mapper;

import com.example.transaction.domain.model.dto.CarDto;
import com.example.transaction.domain.model.entity.Car;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
@Mapper(componentModel = "spring")
public interface CarDtoMapper {
    @Mapping(source = "idArticle", target = "idArticle")
    @Mapping(source = "quantity", target = "quantity")
    @Mapping(source = "state", target = "state")
    @Mapping(source = "price", target = "price")
    @Mapping(source = "date", target = "date")
    CarDto toDto (Car objectOfDomain);
}
