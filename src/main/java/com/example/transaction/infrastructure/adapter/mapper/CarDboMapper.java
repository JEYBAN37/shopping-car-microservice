package com.example.transaction.infrastructure.adapter.mapper;

import com.example.transaction.domain.model.dto.CarDto;
import com.example.transaction.domain.model.entity.Car;
import com.example.transaction.infrastructure.adapter.entity.CarEntity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;


@Component
@AllArgsConstructor
public class CarDboMapper {

public CarEntity toDatabase (Car domain){
    if(domain == null){
        return null;
    }
    return new CarEntity(
            domain.getId(),
            domain.getIdArticle(),
            domain.getQuantity(),
            domain.getState(),
            domain.getPrice(),
            domain.getDate()
    );
}

    public Car toDomain(CarEntity entity){
        if(entity == null){
            return null;
        }
        return new Car(
                entity.getId(),
                entity.getIdArticle(),
                entity.getQuantity(),
                entity.getState(),
                entity.getPrice()
                );
    }

    public CarEntity toDto (CarDto carDto) {
        if(carDto == null){
            return null;
        }
        return new CarEntity(
                carDto.getId(),
                carDto.getIdArticle(),
                carDto.getQuantity(),
                carDto.getState(),
                carDto.getPrice(),
                carDto.getDate()
        );
    }

    public CarDto toDto (CarEntity carEntity) {
        if(carEntity == null){
            return null;
        }
        return new CarDto(
                carEntity.getId(),
                carEntity.getIdArticle(),
                carEntity.getQuantity(),
                carEntity.getState(),
                carEntity.getPrice(),
                carEntity.getDate()
        );
    }


}
