package com.example.transaction.domain.port.repository;
import com.example.transaction.domain.model.entity.Article;


public interface ArticleRepository {
    Article create (Article request);
    Article update (Article request);
}
