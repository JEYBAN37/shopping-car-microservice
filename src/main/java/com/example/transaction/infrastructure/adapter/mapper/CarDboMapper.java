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

    public CarEntity toDatabase(Car domain) {
        if (domain == null) {
            return null;
        }
        List<ArticleEntity> articleEntities = domain.getArticles() == null ? null : domain.getArticles().stream()
                .map(article -> new ArticleEntity(
                        article.getId(),
                        article.getQuantity(),
                        article.getState(),
                        article.getCategories()
                )).toList();

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
        List<Article> articles = entity.getArticles() == null ? null :entity.getArticles().stream().map(
           articleEntity -> new Article(
                   articleEntity.getId(),
                   articleEntity.getQuantity(),
                   articleEntity.getState(),
                   articleEntity.getCategories())
        ).toList();

        return new Car(
                entity.getIdUser(),
                entity.getDateUpdate(),
                articles,
                entity.getDateCreate()
                );
    }

}
