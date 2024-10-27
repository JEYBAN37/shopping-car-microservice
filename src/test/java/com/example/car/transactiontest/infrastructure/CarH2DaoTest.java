package com.example.car.transactiontest.infrastructure;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

import com.example.car.domain.model.entity.Car;
import com.example.car.infrastructure.adapter.entity.CarEntity;
import com.example.car.infrastructure.adapter.jpa.CarSpringJpaAdapterRepository;
import com.example.car.infrastructure.adapter.jpa.dao.CarH2Dao;
import com.example.car.infrastructure.adapter.mapper.CarDboMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

public class CarH2DaoTest {

    @Mock
    private CarSpringJpaAdapterRepository carSpringJpaAdapterRepository;

    @Mock
    private CarDboMapper carDboMapper;

    @InjectMocks
    private CarH2Dao carH2Dao;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetCar_Found() {
        // Arrange
        Long carId = 1L;
        CarEntity carEntity = new CarEntity(/* inicializa con los datos necesarios */);
        Car expectedCar = new Car(/* inicializa con los datos necesarios */);

        when(carSpringJpaAdapterRepository.findById(carId)).thenReturn(Optional.of(carEntity));
        when(carDboMapper.toDomain(carEntity)).thenReturn(expectedCar);

        // Act
        Car result = carH2Dao.getCar(carId);

        // Assert
        assertEquals(expectedCar, result);
        verify(carSpringJpaAdapterRepository).findById(carId);
    }

    @Test
    void testGetCar_NotFound() {
        // Arrange
        Long carId = 2L;
        when(carSpringJpaAdapterRepository.findById(carId)).thenReturn(Optional.empty());

        // Act
        Car result = carH2Dao.getCar(carId);

        // Assert
        assertNull(result);
        verify(carSpringJpaAdapterRepository).findById(carId);
    }

    @Test
    void testIdExist_Exists() {
        // Arrange
        Long carId = 3L;
        when(carSpringJpaAdapterRepository.existsById(carId)).thenReturn(true);

        // Act
        boolean exists = carH2Dao.idExist(carId);

        // Assert
        assertTrue(exists);
        verify(carSpringJpaAdapterRepository).existsById(carId);
    }

    @Test
    void testIdExist_NotExists() {
        // Arrange
        Long carId = 4L;
        when(carSpringJpaAdapterRepository.existsById(carId)).thenReturn(false);

        // Act
        boolean exists = carH2Dao.idExist(carId);

        // Assert
        assertFalse(exists);
        verify(carSpringJpaAdapterRepository).existsById(carId);
    }
}
