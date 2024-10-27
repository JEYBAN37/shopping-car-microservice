package com.example.car.transactiontest;

import com.example.car.domain.model.dto.command.CarEditCommand;
import com.example.car.domain.model.entity.Article;
import com.example.car.domain.model.entity.Car;
import com.example.car.domain.model.entity.articlevalidate.State;
import com.example.car.domain.model.exception.CarException;
import com.example.car.domain.port.dao.CarDao;
import com.example.car.domain.port.repository.CarRepository;
import com.example.car.domain.port.services.ArticleService;
import com.example.car.domain.service.CarUpdateService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.time.LocalDateTime;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CarUpdateServiceTest {

    private CarRepository carRepository;
    private CarDao carDao;
    private ArticleService articleService;
    private CarUpdateService carUpdateService;

    @BeforeEach
    void setUp() {
        carRepository = mock(CarRepository.class);
        carDao = mock(CarDao.class);
        articleService = mock(ArticleService.class);
        carUpdateService = new CarUpdateService(carRepository, carDao, articleService);
    }

    @Test
    void testExecuteCarUpdateCarNotExist() {
        Long carId = 1L;
        CarEditCommand editCommand = new CarEditCommand(Map.of(1L, 2));

        when(carDao.getCar(carId)).thenReturn(null);

        CarException exception = assertThrows(CarException.class, () -> {
            carUpdateService.execute(editCommand, carId);
        });

        assertEquals("Car of User Not Exist", exception.getErrorMessage());
    }

    @Test
    void testUpdateCarArticlesArticleNotExist() {
        Long carId = 1L;
        CarEditCommand editCommand = new CarEditCommand(Map.of(3L, 1)); // Article ID 3 does not exist

        List<Article> currentArticles = new ArrayList<>();
        currentArticles.add(new Article(1L, 5, State.AVAILABLE, List.of(1L)));

        Car car = new Car(carId, LocalDateTime.now(), currentArticles, LocalDateTime.now());
        when(carDao.getCar(carId)).thenReturn(car);
        when(articleService.getArticlesOnlyIds(List.of(3L))).thenReturn(Collections.emptyList());

        CarException exception = assertThrows(CarException.class, () -> {
            carUpdateService.execute(editCommand, carId);
        });

        assertEquals("Article no exists", exception.getErrorMessage());
    }

    @Test
    void testValidateStockInsufficientQuantity() {
        Long carId = 1L;
        CarEditCommand editCommand = new CarEditCommand(Map.of(1L, 2));

        List<Article> currentArticles = new ArrayList<>();
        currentArticles.add(new Article(1L, 1, State.AVAILABLE, List.of(1L))); // Only 1 in stock

        Car car = new Car(carId, LocalDateTime.now(), currentArticles, LocalDateTime.now());
        when(carDao.getCar(carId)).thenReturn(car);
        when(articleService.getArticlesOnlyIds(List.of(1L))).thenReturn(currentArticles);

        CarException exception = assertThrows(CarException.class, () -> {
            carUpdateService.execute(editCommand, carId);
        });

        assertEquals(" id 1 no quantity in Stock", exception.getErrorMessage());
    }

    @Test
    void testHasCategoriesWithThreeOccurrences() {
        List<Article> updatedArticles = new ArrayList<>();
        updatedArticles.add(new Article(1L, 2, State.AVAILABLE,  List.of(1L)));
        updatedArticles.add(new Article(2L, 2, State.AVAILABLE,  List.of(1L)));
        updatedArticles.add(new Article(3L, 2, State.AVAILABLE,  List.of(2L)));
        updatedArticles.add(new Article(4L, 2, State.AVAILABLE, List.of(1L)));

        assertTrue(carUpdateService.hasCategoriesWithThreeOccurrences(updatedArticles));

        updatedArticles.add(new Article(5L, 2, State.AVAILABLE,  List.of(1L))); // Now has more than 3 occurrences
        assertFalse(carUpdateService.hasCategoriesWithThreeOccurrences(updatedArticles));
    }
}
