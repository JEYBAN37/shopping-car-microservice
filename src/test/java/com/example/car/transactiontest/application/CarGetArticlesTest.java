package com.example.car.transactiontest.application;

import com.example.car.application.command.FeignClientInterceptorHandler;
import com.example.car.application.command.JwtHandler;
import com.example.car.application.mapper.ArticleDtoMapper;
import com.example.car.application.query.CarGetArticles;
import com.example.car.domain.model.dto.ArticleToGetDto;
import com.example.car.domain.model.entity.Article;
import com.example.car.domain.service.CarGetService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class CarGetArticlesTest {

    @Mock
    private CarGetService carGetService;

    @Mock
    private ArticleDtoMapper articleDtoMapper;

    @Mock
    private JwtHandler jwtHandler;

    @Mock
    private FeignClientInterceptorHandler feignClientInterceptorHandler;

    @InjectMocks
    private CarGetArticles carGetArticles;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }


    @Test
    public void testExecute_NoArticles_ReturnsEmptyList() {
        // Arrange
        String token = "Bearer someToken";
        Long userId = 1L;
        when(jwtHandler.getUserIdFromToken("someToken")).thenReturn(Integer.valueOf("1"));
        when(carGetService.execute(eq(userId), anyInt(), anyInt(), anyBoolean(), anyString(), anyString(), anyString()))
                .thenReturn(Collections.emptyList());

        // Act
        List<ArticleToGetDto> result = carGetArticles.execute(token, 0, 10, true, null, null, null);

        // Assert
        assertEquals(Collections.emptyList(), result);
        verify(feignClientInterceptorHandler).sendToken("someToken");
    }

    // Agrega más pruebas según sea necesario
}
