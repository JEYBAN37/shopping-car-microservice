package com.example.transaction.infrastructure.adapter.http;

import com.example.transaction.domain.model.entity.Article;
import com.example.transaction.domain.model.entity.ArticleToGet;
import com.example.transaction.domain.port.services.ArticleService;
import com.example.transaction.infrastructure.adapter.http.client.ArticleClient;
import com.example.transaction.infrastructure.adapter.json.ArticleJson;
import com.example.transaction.infrastructure.adapter.mapper.ArticleDboMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

@AllArgsConstructor
@Service
public class HttpArticleService implements ArticleService {
    ArticleClient articleClient;
    ArticleDboMapper articleDboMapper;

    @Override
    public List<Article> getArticlesOnlyIds(List<Long> ids) {
        Optional<List<ArticleJson>> optionalArticles = articleClient.articles(ids,
                0,
                100,
                false,
                null,
                null,
                null);

        return optionalArticles.map(articleJsons -> articleJsons.stream()
                .map(articleDboMapper::toDomainJson)
                .toList()).orElse(Collections.emptyList());
    }

    @Override
    public List<ArticleToGet> getArticlesByIds(List<Article> articles, int page, int size, boolean ascending, String byBrand, String byName, String byCategory) {
        List<Long> idarticles =  articles.stream().map(Article::getId).toList();

        Optional<List<ArticleJson>> optionalArticles = articleClient
                .articles(idarticles,
                        page,
                        size,
                        ascending,
                        byBrand,
                        byName,
                        byCategory);

        if (optionalArticles.isPresent())
            return toArticleToGet(optionalArticles.get(),articles);

        return Collections.emptyList();

    }

    private List<ArticleToGet> toArticleToGet(List<ArticleJson> articleJsons,List<Article> articles){
        return IntStream.range(0, articleJsons.size())
                .mapToObj(i -> articleDboMapper.toController(articleJsons.get(i), articles.get(i)))
                .toList();
    }

}
