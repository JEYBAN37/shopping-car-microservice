package com.example.transaction.application.mapper;

import com.example.transaction.domain.model.dto.CarDto;
import com.example.transaction.domain.model.entity.Car;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
@Mapper(componentModel = "spring")
public interface CarDtoMapper {
    @Mapping(source = "idUser", target = "idUser")
    @Mapping(source = "articles", target = "articles")
    @Mapping(source = "dateUpdate", target = "dateUpdate")
    @Mapping(source = "dateCreate", target = "dateCreate")
    CarDto toDto (Car objectOfDomain);
}
