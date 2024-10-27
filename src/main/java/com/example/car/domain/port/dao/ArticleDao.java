package com.example.car.domain.port.dao;

import com.example.car.domain.model.entity.Article;

import java.util.List;

public interface ArticleDao {
    List<Article> getArticlesCar(Long id );
}
