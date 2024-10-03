package com.example.transaction.transactiontest;

import com.example.transaction.domain.model.entity.Car;
import com.example.transaction.infrastructure.adapter.entity.CarEntity;
import com.example.transaction.infrastructure.adapter.jpa.CarSpringJpaAdapterRepository;
import com.example.transaction.infrastructure.adapter.jpa.respository.CarH2Repository;
import com.example.transaction.infrastructure.adapter.mapper.CarDboMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CarH2RepositoryTest {

    @Mock
    private CarSpringJpaAdapterRepository carSpringJpaAdapterRepository;

    @Mock
    private CarDboMapper carDboMapper;

    @InjectMocks
    private CarH2Repository supplyH2Repository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void create_ShouldReturnSupply_WhenSupplyIsCreated() {
        // Arrange
        Car carToCreate = new Car(1L,2,5,"e",new BigDecimal(54));
        CarEntity carEntityToSave = new CarEntity();
        CarEntity savedCarEntity = new CarEntity();
        Car expectedCar = new Car();

        when(carDboMapper.toDatabase(carToCreate)).thenReturn(carEntityToSave);
        when(carSpringJpaAdapterRepository.save(carEntityToSave)).thenReturn(savedCarEntity);
        when(carDboMapper.toDomain(savedCarEntity)).thenReturn(expectedCar);

        // Act
        Car result = supplyH2Repository.create(carToCreate);

        // Assert
        assertNotNull(result);
        assertEquals(expectedCar, result);
        verify(carDboMapper, times(1)).toDatabase(carToCreate);
        verify(carSpringJpaAdapterRepository, times(1)).save(carEntityToSave);
        verify(carDboMapper, times(1)).toDomain(savedCarEntity);
    }

    @Test
     void update_ShouldReturnUpdatedSupply_WhenSupplyIsUpdated() {
        // Arrange
        Car carToUpdate = new Car();
        CarEntity carEntityToUpdate = new CarEntity();
        CarEntity updatedCarEntity = new CarEntity();
        Car expectedCar = new Car();

        when(carDboMapper.toDatabase(carToUpdate)).thenReturn(carEntityToUpdate);
        when(carSpringJpaAdapterRepository.save(carEntityToUpdate)).thenReturn(updatedCarEntity);
        when(carDboMapper.toDomain(updatedCarEntity)).thenReturn(expectedCar);

        // Act
        Car result = supplyH2Repository.update(carToUpdate);

        // Assert
        assertNotNull(result);
        assertEquals(expectedCar, result);
        verify(carDboMapper, times(1)).toDatabase(carToUpdate);
        verify(carSpringJpaAdapterRepository, times(1)).save(carEntityToUpdate);
        verify(carDboMapper, times(1)).toDomain(updatedCarEntity);
    }
}
