package com.example.transaction.application.mapper;

import com.example.transaction.domain.model.dto.CarDto;
import com.example.transaction.domain.model.entity.Car;
import org.mapstruct.Mapper;
@Mapper(componentModel = "spring")
public interface CarDtoMapper {
    CarDto toDto (Car objectOfDomain);
}
