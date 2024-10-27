package com.example.car.transactiontest;

import com.example.car.domain.model.entity.Article;
import com.example.car.domain.model.entity.ArticleToGet;
import com.example.car.domain.model.entity.articlevalidate.State;
import com.example.car.domain.model.exception.CarException;
import com.example.car.domain.port.dao.ArticleDao;
import com.example.car.domain.port.services.ArticleService;
import com.example.car.domain.service.CarGetService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class CarGetServiceTest {

    @Mock
    private ArticleDao articleDao;

    @Mock
    private ArticleService articleService;

    @InjectMocks
    private CarGetService carGetService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testExecuteWithValidParameters() {
        Long carId = 1L;
        int page = 0;
        int size = 10;
        boolean ascending = true;
        String byBrand = "BrandName";
        String byName = "ArticleName";
        String byCategory = "CategoryName";

        // Simular que el carro tiene artículos
        List<Article> articles = new ArrayList<>();
        articles.add(new Article(1L,4,State.AVAILABLE,null)); // Asumir que tienes un constructor para Article

        when(articleDao.getArticlesCar(carId)).thenReturn(articles);

        List<ArticleToGet> expectedArticles = new ArrayList<>();
        expectedArticles.add(new ArticleToGet(1L, "ArticleName", "BrandName", 4,"r",new ArrayList<String>(),new BigDecimal(5), State.AVAILABLE)); // Simular que tienes un constructor

        when(articleService.getArticlesByIds(articles, page, size, ascending, byBrand, byName, byCategory))
                .thenReturn(expectedArticles);

        List<ArticleToGet> result = carGetService.execute(carId, page, size, ascending, byBrand, byName, byCategory);

        // Verificaciones
        assertEquals(expectedArticles, result);
        verify(articleDao, times(1)).getArticlesCar(carId);
        verify(articleService, times(1)).getArticlesByIds(articles, page, size, ascending, byBrand, byName, byCategory);
    }

    @Test
    void testExecuteWithNegativePage() {
        Long carId = 1L;
        int page = -1;
        int size = 10;
        boolean ascending = true;
        String byBrand = "BrandName";
        String byName = "ArticleName";
        String byCategory = "CategoryName";

        assertThrows(CarException.class, () -> carGetService.execute(carId, page, size, ascending, byBrand, byName, byCategory));

        verify(articleDao, never()).getArticlesCar(carId);
        verify(articleService, never()).getArticlesByIds(anyList(), anyInt(), anyInt(), anyBoolean(), anyString(), anyString(), anyString());
    }

    @Test
    void testExecuteWithZeroSize() {
        Long carId = 1L;
        int page = 0;
        int size = 0;
        boolean ascending = true;
        String byBrand = "BrandName";
        String byName = "ArticleName";
        String byCategory = "CategoryName";

        assertThrows(CarException.class, () -> carGetService.execute(carId, page, size, ascending, byBrand, byName, byCategory));

        verify(articleDao, never()).getArticlesCar(carId);
        verify(articleService, never()).getArticlesByIds(anyList(), anyInt(), anyInt(), anyBoolean(), anyString(), anyString(), anyString());
    }

    @Test
    void testExecuteWithNoArticles() {
        Long carId = 1L;
        int page = 0;
        int size = 10;
        boolean ascending = true;
        String byBrand = "BrandName";
        String byName = "ArticleName";
        String byCategory = "CategoryName";

        // Simular que no hay artículos para el carro
        when(articleDao.getArticlesCar(carId)).thenReturn(Collections.emptyList());

        List<ArticleToGet> expectedArticles = new ArrayList<>();

        when(articleService.getArticlesByIds(Collections.emptyList(), page, size, ascending, byBrand, byName, byCategory))
                .thenReturn(expectedArticles);

        List<ArticleToGet> result = carGetService.execute(carId, page, size, ascending, byBrand, byName, byCategory);

        // Verificaciones
        assertEquals(expectedArticles, result);
        verify(articleDao, times(1)).getArticlesCar(carId);
        verify(articleService, times(1)).getArticlesByIds(Collections.emptyList(), page, size, ascending, byBrand, byName, byCategory);
    }
}
