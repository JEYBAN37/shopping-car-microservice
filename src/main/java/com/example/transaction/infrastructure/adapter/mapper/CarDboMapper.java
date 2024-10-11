package com.example.transaction.infrastructure.adapter.mapper;

import com.example.transaction.domain.model.entity.Article;
import com.example.transaction.domain.model.entity.Car;
import com.example.transaction.infrastructure.adapter.entity.ArticleEntity;
import com.example.transaction.infrastructure.adapter.entity.CarEntity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
@AllArgsConstructor
public class CarDboMapper {

public CarEntity toDatabase (Car domain){
    if(domain == null){
        return null;
    }
    List<ArticleEntity> articleEntities = domain.getArticles().stream()
            .map(category -> new ArticleEntity(category.getId(),
                    category.getQuantity(),
                    category.getState())).toList();

    return new CarEntity(
            domain.getIdUser(),
            articleEntities,
            domain.getDateUpdate(),
            domain.getDateCreate()
    );
}

    public Car toDomain(CarEntity entity){
        if(entity == null){
            return null;
        }
        List<Article> articles = entity.getArticles().stream().map(
           articleEntity -> new Article(articleEntity.getId(),articleEntity.getQuantity(),articleEntity.getState())
        ).toList();

        return new Car(
                entity.getIdUser(),
                entity.getDateUpdate(),
                articles
                );
    }

//    public CarEntity toDto (CarDto carDto) {
//        if(carDto == null){
//            return null;
//        }
//        return new CarEntity(
//                carDto.getId(),
//                carDto.getIdArticles(),
//                carDto.getIdUser(),
//                carDto.getQuantity(),
//                carDto.getDateUpdate(),
//                carDto.getDateCreate()
//        );
//    }
//
//    public CarDto toDto (CarEntity carEntity) {
//        if(carEntity == null){
//            return null;
//        }
//        return new CarDto(
//                carEntity.getId(),
//                carEntity.getIdUser(),
//                carEntity.getIdArticles(),
//                carEntity.getQuantity(),
//                carEntity.getDateUpdate(),
//                carEntity.getDateCreate()
//        );
//    }


}
