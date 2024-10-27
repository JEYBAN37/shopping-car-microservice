package com.example.car.transactiontest.infrastructure;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import com.example.car.domain.model.entity.Article;
import com.example.car.domain.model.entity.ArticleToGet;
import com.example.car.domain.model.entity.articlevalidate.State;
import com.example.car.infrastructure.adapter.http.HttpArticleService;
import com.example.car.infrastructure.adapter.http.client.ArticleClient;
import com.example.car.infrastructure.adapter.json.ArticleJson;
import com.example.car.infrastructure.adapter.json.BrandJson;
import com.example.car.infrastructure.adapter.json.CategoriesJson;
import com.example.car.infrastructure.adapter.mapper.ArticleDboMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public class HttpArticleServiceTest {

    @Mock
    private ArticleClient articleClient;

    @Mock
    private ArticleDboMapper articleDboMapper;

    @InjectMocks
    private HttpArticleService httpArticleService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetArticlesOnlyIds_Success() {
        // Arrange
        List<Long> ids = List.of(1L, 2L);
        ArticleJson articleJson1 = new ArticleJson(1L, 8,"name",  new BrandJson(),new BigDecimal(5),"category",List.of(new CategoriesJson()));
        ArticleJson articleJson2 =  new ArticleJson(2L, 8,"name",  new BrandJson(),new BigDecimal(5),"category",List.of(new CategoriesJson()));
        List<ArticleJson> articleJsons = List.of(articleJson1, articleJson2);
        List<Long> categories = List.of(1L, 2L);

        when(articleClient.articles(eq(ids), eq(0), eq(100), eq(false), isNull(), isNull(), isNull()))
                .thenReturn(Optional.of(articleJsons));
        when(articleDboMapper.toDomainJson(articleJson1)).thenReturn(new Article(1L, 8, State.AVAILABLE,categories));
        when(articleDboMapper.toDomainJson(articleJson2)).thenReturn(new Article(2L, 8, State.AVAILABLE,categories));

        // Act
        List<Article> result = httpArticleService.getArticlesOnlyIds(ids);

        // Assert
        assertEquals(2, result.size());
        verify(articleClient).articles(eq(ids), eq(0), eq(100), eq(false), isNull(), isNull(), isNull());
    }

    @Test
    void testGetArticlesOnlyIds_NoArticles() {
        // Arrange
        List<Long> ids = List.of(1L, 2L);
        when(articleClient.articles(eq(ids), eq(0), eq(100), eq(false), isNull(), isNull(), isNull()))
                .thenReturn(Optional.empty());

        // Act
        List<Article> result = httpArticleService.getArticlesOnlyIds(ids);

        // Assert
        assertTrue(result.isEmpty());
        verify(articleClient).articles(eq(ids), eq(0), eq(100), eq(false), isNull(), isNull(), isNull());
    }
    @Test
    void testGetArticlesByIds_Success() {
        // Arrange
        List<Long> categories = List.of(1L, 2L);
        List<String> cat = List.of("cat1", "cat2");
        Article article = new Article(1L, 8, State.AVAILABLE,categories); // Inicializa tu objeto Article
        List<Article> articles = List.of(article);
        List<Long> ids = List.of(article.getId());
        ArticleJson articleJson = new ArticleJson(1L, 8,"name",  new BrandJson(),new BigDecimal(5),"category",List.of(new CategoriesJson()));

        when(articleClient.articles(eq(ids), anyInt(), anyInt(), anyBoolean(), isNull(), isNull(), isNull()))
                .thenReturn(Optional.of(List.of(articleJson)));
        when(articleDboMapper.toController(articleJson, article)).thenReturn(new ArticleToGet(1L, "name","descrip",8,"fds",cat,new BigDecimal(8), State.AVAILABLE));

        // Act
        List<ArticleToGet> result = httpArticleService.getArticlesByIds(articles, 0, 100, false, null, null, null);

        // Assert
        assertEquals(1, result.size());
        verify(articleClient).articles(eq(ids), anyInt(), anyInt(), anyBoolean(), isNull(), isNull(), isNull());
    }
}
