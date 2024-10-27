package com.example.car.transactiontest;

import com.example.car.domain.model.dto.command.CarDeleteCommand;
import com.example.car.domain.model.entity.Article;
import com.example.car.domain.model.entity.Car;
import com.example.car.domain.model.entity.articlevalidate.State;
import com.example.car.domain.model.exception.CarException;
import com.example.car.domain.port.dao.CarDao;
import com.example.car.domain.port.repository.CarRepository;
import com.example.car.domain.service.CarArticleDeleteService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class CarArticleDeleteServiceTest {

    @Mock
    private CarRepository carRepository;

    @Mock
    private CarDao carDao;

    @InjectMocks
    private CarArticleDeleteService carArticleDeleteService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }


    @Test
    void testExecuteWithNonExistingCar() {
        Long carId = 1L;
        CarDeleteCommand command = new CarDeleteCommand(2L,LocalDateTime.now());

        when(carDao.getCar(carId)).thenReturn(null);

        assertThrows(CarException.class, () -> carArticleDeleteService.execute(carId, command));
        verify(carDao, times(1)).getCar(carId);
        verify(carRepository, never()).update(any(Car.class));
    }

    @Test
    void testExecuteWithNonExistingArticle() {
        Long carId = 1L;
        Long articleId = 2L;
        CarDeleteCommand command = new CarDeleteCommand(articleId,LocalDateTime.now());

        Article anotherArticle = new Article(articleId,0, State.AVAILABLE,new ArrayList<Long>());
        List<Article> articles = new ArrayList<>();
        articles.add(anotherArticle);

        Car car = new Car(carId, LocalDateTime.now(),articles, LocalDateTime.now());

        when(carDao.getCar(carId)).thenReturn(car);

        assertThrows(CarException.class, () -> carArticleDeleteService.execute(carId, command));
        verify(carDao, times(1)).getCar(carId);
        verify(carRepository, never()).update(any(Car.class));
    }
}
