package com.example.car.transactiontest.infrastructure;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import com.example.car.domain.model.entity.Article;
import com.example.car.domain.model.entity.articlevalidate.State;
import com.example.car.infrastructure.adapter.entity.ArticleEntity;
import com.example.car.infrastructure.adapter.jpa.ArticleSpringJpaAdapterRepository;
import com.example.car.infrastructure.adapter.jpa.dao.ArticleH2Dao;
import com.example.car.infrastructure.adapter.mapper.ArticleDboMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

public class ArticleH2DaoTest {
    @Mock
    private ArticleDboMapper articleDboMapper;

    @Mock
    private ArticleSpringJpaAdapterRepository articleSpringJpaAdapterRepository;

    @InjectMocks
    private ArticleH2Dao articleH2Dao;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetArticlesCar() {
        Long carId = 1L;

        // Simular una lista de entidades de artículos
        ArticleEntity articleEntity1 = new ArticleEntity(1L,8, State.AVAILABLE, List.of(1L, 2L));
        ArticleEntity articleEntity2 = new ArticleEntity(2L,8, State.AVAILABLE, List.of(1L, 2L));
        List<ArticleEntity> articleEntities = List.of(articleEntity1, articleEntity2);

        // Simular el comportamiento del repositorio
        when(articleSpringJpaAdapterRepository.findAllByCarId(carId)).thenReturn(articleEntities);

        // Simular el comportamiento del mapeador
        when(articleDboMapper.toDomain(articleEntity1)).thenReturn(new Article(1L,8, State.AVAILABLE, List.of(1L, 2L)));
        when(articleDboMapper.toDomain(articleEntity2)).thenReturn(new Article(2L,8, State.AVAILABLE, List.of(1L, 2L)));

        // Llamar al método
        List<Article> articles = articleH2Dao.getArticlesCar(carId);

        // Verificar resultados
        assertNotNull(articles);
        assertEquals(2, articles.size()); // Verifica que se devuelvan los artículos esperados

        // Verificar interacciones con el mock
        verify(articleSpringJpaAdapterRepository).findAllByCarId(carId);
        verify(articleDboMapper).toDomain(articleEntity1);
        verify(articleDboMapper).toDomain(articleEntity2);
    }

    @Test
    public void testGetArticlesCar_EmptyList() {
        Long carId = 2L;

        // Simular que no se devuelven artículos
        when(articleSpringJpaAdapterRepository.findAllByCarId(carId)).thenReturn(List.of());

        // Llamar al método
        List<Article> articles = articleH2Dao.getArticlesCar(carId);

        // Verificar resultados
        assertNotNull(articles);
        assertTrue(articles.isEmpty()); // Verifica que la lista está vacía

        // Verificar interacción con el mock
        verify(articleSpringJpaAdapterRepository).findAllByCarId(carId);
    }
}
