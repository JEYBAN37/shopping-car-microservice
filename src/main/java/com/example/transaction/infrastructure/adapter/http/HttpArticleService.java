package com.example.transaction.infrastructure.adapter.http;

import com.example.transaction.domain.model.entity.Article;
import com.example.transaction.domain.port.services.ArticleService;
import com.example.transaction.infrastructure.adapter.http.client.ArticleClient;
import com.example.transaction.infrastructure.adapter.json.ArticleJson;
import com.example.transaction.infrastructure.adapter.mapper.ArticleDboMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class HttpArticleService implements ArticleService {
    ArticleClient articleClient;
    ArticleDboMapper articleDboMapper;

    @Override
    public List<Article> getArticlesByIds(List<Long> ids) {
        Optional<List<ArticleJson>> optionalArticles = articleClient.articles(ids);

        return optionalArticles.map(articleJsons -> articleJsons.stream()
                .map(articleDboMapper::toDomain)
                .toList()).orElse(Collections.emptyList());
    }

}
