package com.example.transaction.domain.port.services;

import com.example.transaction.domain.model.entity.Article;

import java.util.List;


public interface ArticleService {
    List<Article> getArticlesByIds (List<Long> id);


}
