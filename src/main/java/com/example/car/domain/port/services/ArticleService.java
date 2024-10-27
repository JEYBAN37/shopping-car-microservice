package com.example.car.domain.port.services;

import com.example.car.domain.model.entity.Article;
import com.example.car.domain.model.entity.ArticleToGet;

import java.util.List;


public interface ArticleService {
    List<Article> getArticlesOnlyIds(List<Long> id);
    List<ArticleToGet> getArticlesByIds (List<Article> id,int page,
                                    int size,
                                    boolean ascending,
                                    String byBrand,
                                    String byName,
                                    String byCategory);

}
