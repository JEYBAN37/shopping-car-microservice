package com.example.transaction.transactiontest;

import com.example.transaction.domain.model.entity.Car;
import com.example.transaction.infrastructure.adapter.entity.CarEntity;
import com.example.transaction.infrastructure.adapter.jpa.CarSpringJpaAdapterRepository;
import com.example.transaction.infrastructure.adapter.jpa.dao.CarH2Dao;
import com.example.transaction.infrastructure.adapter.mapper.CarDboMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.sql.Timestamp;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

 class CarH2DaoTest {

    @Mock
    private CarSpringJpaAdapterRepository carSpringJpaAdapterRepository;

    @Mock
    private CarDboMapper carDboMapper;

    @InjectMocks
    private CarH2Dao supplyH2Dao;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
     void getSupply_ShouldReturnSupply_WhenSupplyExists() {
        // Arrange
        Long supplyId = 1L;
        CarEntity carEntity = new CarEntity(); // Suponiendo que CarEntity tiene un constructor vacío
        Car car = new Car(); // Suponiendo que Car tiene un constructor vacío

        when(carSpringJpaAdapterRepository.findById(supplyId)).thenReturn(Optional.of(carEntity));
        when(carDboMapper.toDomain(carEntity)).thenReturn(car);

        // Act
        Car result = supplyH2Dao.getSupply(supplyId);

        // Assert
        assertNotNull(result);
        assertEquals(car, result);
        verify(carSpringJpaAdapterRepository, times(1)).findById(supplyId);
        verify(carDboMapper, times(1)).toDomain(carEntity);
    }

    @Test
     void getSupply_ShouldReturnNull_WhenSupplyDoesNotExist() {
        // Arrange
        Long supplyId = 1L;

        when(carSpringJpaAdapterRepository.findById(supplyId)).thenReturn(Optional.empty());

        // Act
        Car result = supplyH2Dao.getSupply(supplyId);

        // Assert
        assertNull(result);
        verify(carSpringJpaAdapterRepository, times(1)).findById(supplyId);
        verify(carDboMapper, never()).toDomain(any(CarEntity.class)); // Verificar que no se llama toDomain
    }

    @Test
     void idExist_ShouldReturnTrue_WhenIdExists() {
        // Arrange
        Long existingId = 1L;

        when(carSpringJpaAdapterRepository.existsById(existingId)).thenReturn(true);

        // Act
        boolean result = supplyH2Dao.idExist(existingId);

        // Assert
        assertTrue(result);
        verify(carSpringJpaAdapterRepository, times(1)).existsById(existingId);
    }

    @Test
     void idExist_ShouldReturnFalse_WhenIdDoesNotExist() {
        // Arrange
        Long nonExistingId = 1L;

        when(carSpringJpaAdapterRepository.existsById(nonExistingId)).thenReturn(false);

        // Act
        boolean result = supplyH2Dao.idExist(nonExistingId);

        // Assert
        assertFalse(result);
        verify(carSpringJpaAdapterRepository, times(1)).existsById(nonExistingId);
    }

    @Test
     void dateExist_ShouldReturnFalse() {
        // Act
        boolean result = supplyH2Dao.dateExist(Timestamp.valueOf("2024-01-01 00:00:00"));

        // Assert
        assertFalse(result);
    }
}
