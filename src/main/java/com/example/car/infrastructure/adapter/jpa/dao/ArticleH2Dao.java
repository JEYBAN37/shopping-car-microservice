package com.example.car.infrastructure.adapter.jpa.dao;

import com.example.car.domain.model.entity.Article;
import com.example.car.domain.port.dao.ArticleDao;
import com.example.car.infrastructure.adapter.jpa.ArticleSpringJpaAdapterRepository;
import com.example.car.infrastructure.adapter.mapper.ArticleDboMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import java.util.List;


@AllArgsConstructor
@Repository
public class ArticleH2Dao implements ArticleDao {
    private final ArticleDboMapper articleDboMapper;
    private final ArticleSpringJpaAdapterRepository articleSpringJpaAdapterRepository;

    @Override
    public List<Article> getArticlesCar(Long id) {
        return articleSpringJpaAdapterRepository
                .findAllByCarId(id)
                .stream()
                .map(articleDboMapper::toDomain)
                .toList();
    }
}
