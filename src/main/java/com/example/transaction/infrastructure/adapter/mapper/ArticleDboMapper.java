package com.example.transaction.infrastructure.adapter.mapper;

import com.example.transaction.domain.model.entity.Article;
import com.example.transaction.domain.model.entity.ArticleToGet;
import com.example.transaction.domain.model.entity.articlevalidate.State;
import com.example.transaction.infrastructure.adapter.entity.ArticleEntity;
import com.example.transaction.infrastructure.adapter.json.ArticleJson;
import com.example.transaction.infrastructure.adapter.json.CategoriesJson;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

import static com.example.transaction.domain.model.constant.CarConstant.ZERO_CONSTANT;

@Component
public class ArticleDboMapper {

    public Article toDomainJson (ArticleJson json){
        if(json == null){
            return null;
        }

        State state = State.AVAILABLE;

        if (json.getQuantity() == ZERO_CONSTANT)
            state = State.SPENT;

        List<Long> categoriesId = json.getArticleCategories().stream()
                .map(CategoriesJson::getId)
                .toList();

        return new Article(
                json.getId(),
                json.getQuantity(),
                state,
               categoriesId
        );
    }

    public Article toDomain(ArticleEntity entity){
        if(entity == null){
            return null;
        }

        return new Article(
                entity.getId(),
                entity.getQuantity(),
                entity.getState(),
                entity.getCategories()
              );
    }

    public ArticleToGet toController (ArticleJson json,Article article){
        List<String> categories = json.getArticleCategories().stream()
                .map(CategoriesJson::getName)
                .toList();

        return new ArticleToGet(
                article.getId(),
                json.getName(),
                json.getDescription(),
                article.getQuantity(),
                json.getBrand().getName(),
                categories,
                json.getPrice().multiply(BigDecimal.valueOf(article.getQuantity())),
                article.getState()
        );
    }
}
