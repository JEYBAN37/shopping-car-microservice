package com.example.car.domain.service;

import com.example.car.domain.model.entity.Article;
import com.example.car.domain.model.entity.ArticleToGet;
import com.example.car.domain.model.exception.CarException;
import com.example.car.domain.port.dao.ArticleDao;
import com.example.car.domain.port.services.ArticleService;
import lombok.AllArgsConstructor;

import java.util.List;

import static com.example.car.domain.model.constant.CarConstant.MESSAGE_PAGE_VALID;

@AllArgsConstructor
public class CarGetService {
    private final ArticleDao articleDao;
    private final ArticleService articleService;

    public List<ArticleToGet> execute (Long id,
                                       int page,
                                       int size,
                                       boolean ascending,
                                       String byBrand,
                                       String byName,
                                       String byCategory){
        if (page < 0 || size <= 0) {
            throw new CarException(MESSAGE_PAGE_VALID);
        }

        List<Article> ids = articleDao.getArticlesCar(id);

        return articleService.getArticlesByIds(ids,
                                                page,
                                                size,
                                                ascending,
                                                byBrand,
                                                byName,
                                                byCategory);
    }
}
