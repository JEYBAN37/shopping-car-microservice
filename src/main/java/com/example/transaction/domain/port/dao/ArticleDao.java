package com.example.transaction.domain.port.dao;

import com.example.transaction.domain.model.entity.Article;

import java.util.List;

public interface ArticleDao {
    List<Article> getArticlesCar(Long id );
}
