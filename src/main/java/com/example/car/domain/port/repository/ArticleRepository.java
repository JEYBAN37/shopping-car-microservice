package com.example.car.domain.port.repository;
import com.example.car.domain.model.entity.Article;


public interface ArticleRepository {
    Article create (Article request);
    Article update (Article request);
}
