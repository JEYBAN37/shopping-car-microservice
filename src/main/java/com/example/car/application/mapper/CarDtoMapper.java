package com.example.car.application.mapper;

import com.example.car.domain.model.dto.CarDto;
import com.example.car.domain.model.entity.Car;
import org.mapstruct.Mapper;
@Mapper(componentModel = "spring")
public interface CarDtoMapper {
    CarDto toDto (Car objectOfDomain);
}
