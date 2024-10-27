package com.example.car.transactiontest.infrastructure;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import com.example.car.domain.model.entity.Car;
import com.example.car.infrastructure.adapter.entity.CarEntity;
import com.example.car.infrastructure.adapter.jpa.CarSpringJpaAdapterRepository;
import com.example.car.infrastructure.adapter.jpa.respository.CarH2Repository;
import com.example.car.infrastructure.adapter.mapper.CarDboMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class CarH2RepositoryTest {

    @Mock
    private CarSpringJpaAdapterRepository carSpringJpaAdapterRepository;

    @Mock
    private CarDboMapper carDboMapper;

    @InjectMocks
    private CarH2Repository carH2Repository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreate() {
        // Arrange
        Car carToCreate = new Car(/* inicializa con los datos necesarios */);
        CarEntity carEntity = new CarEntity(/* inicializa con los datos necesarios */);
        CarEntity savedCarEntity = new CarEntity(/* inicializa con los datos necesarios */);

        when(carDboMapper.toDatabase(carToCreate)).thenReturn(carEntity);
        when(carSpringJpaAdapterRepository.save(carEntity)).thenReturn(savedCarEntity);
        when(carDboMapper.toDomain(savedCarEntity)).thenReturn(carToCreate);

        // Act
        Car result = carH2Repository.create(carToCreate);

        // Assert
        assertEquals(carToCreate, result);
        verify(carDboMapper).toDatabase(carToCreate);
        verify(carSpringJpaAdapterRepository).save(carEntity);
        verify(carDboMapper).toDomain(savedCarEntity);
    }

    @Test
    void testUpdate() {
        // Arrange
        Car carToUpdate = new Car(/* inicializa con los datos necesarios */);
        CarEntity carEntity = new CarEntity(/* inicializa con los datos necesarios */);
        CarEntity updatedCarEntity = new CarEntity(/* inicializa con los datos necesarios */);

        when(carDboMapper.toDatabase(carToUpdate)).thenReturn(carEntity);
        when(carSpringJpaAdapterRepository.save(carEntity)).thenReturn(updatedCarEntity);
        when(carDboMapper.toDomain(updatedCarEntity)).thenReturn(carToUpdate);

        // Act
        Car result = carH2Repository.update(carToUpdate);

        // Assert
        assertEquals(carToUpdate, result);
        verify(carDboMapper).toDatabase(carToUpdate);
        verify(carSpringJpaAdapterRepository).save(carEntity);
        verify(carDboMapper).toDomain(updatedCarEntity);
    }
}
