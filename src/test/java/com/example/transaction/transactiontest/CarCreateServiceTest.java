package com.example.transaction.transactiontest;

import com.example.transaction.domain.model.dto.command.CarCreateCommand;
import com.example.transaction.domain.model.entity.Car;
import com.example.transaction.domain.model.exception.CarException;
import com.example.transaction.domain.port.dao.CarDao;
import com.example.transaction.domain.port.publisher.SupplyPublisher;
import com.example.transaction.domain.port.repository.CarRepository;
import com.example.transaction.domain.service.CarCreateService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

 class CarCreateServiceTest {

    @Mock
    private CarRepository carRepository;

    @Mock
    private SupplyPublisher supplyPublisher;

    @Mock
    private CarDao carDao;

    @InjectMocks
    private CarCreateService carCreateService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
     void execute_ShouldThrowException_WhenListIsEmpty() {
        // Arrange
        List<CarCreateCommand> createCommands = Collections.emptyList();

        // Act & Assert
        CarException exception = assertThrows(CarException.class, () -> {
            carCreateService.execute(createCommands);
        });

        assertEquals("List Empty", exception.getErrorMessage());
    }

    @Test
     void execute_ShouldCreateSupplies_WhenCommandsAreValid() {
        // Arrange
        List<CarCreateCommand> createCommands = new ArrayList<>();
        CarCreateCommand command1 = new CarCreateCommand(1L,1, 10, "ESTATE" ,new BigDecimal(100.0));
        CarCreateCommand command2 = new CarCreateCommand(2L,2, 20,"ESTATE" , new BigDecimal(200.0));
        createCommands.add(command1);
        createCommands.add(command2);

        Car car1 = new Car();
        Car car2 = new Car();

        when(carDao.idExist(command1.getId())).thenReturn(false);
        when(carDao.idExist(command2.getId())).thenReturn(false);
        when(carRepository.create(any())).thenReturn(car1, car2);

        // Act
        List<Car> result = carCreateService.execute(createCommands);

        // Assert
        assertEquals(2, result.size());
        verify(carRepository, times(2)).create(any());
        verify(supplyPublisher, times(2)).publishMessage(any());
    }

    @Test
    public void execute_ShouldThrowException_WhenSupplyExists() {
        // Arrange
        CarCreateCommand command = new CarCreateCommand(1L,1, 10, "ESTATE" ,new BigDecimal(100.0));
        when(carDao.idExist(command.getId())).thenReturn(true); // Simular que el suministro ya existe

        // Act & Assert
        CarException exception = assertThrows(CarException.class, () -> {
            carCreateService.execute(List.of(command));
        });

        assertEquals("Car Exist", exception.getErrorMessage());
    }

    @Test
    public void execute_ShouldCreateSupplies_WhenCommandsAreValidf() {
        // Arrange
        CarCreateCommand command1 = new CarCreateCommand(1L,1, 10, "ESTATE" ,new BigDecimal(100.0));
        CarCreateCommand command2 =new CarCreateCommand(2L,2, 10, "ESTATE" ,new BigDecimal(100.0));

        // Simulaciones de retorno de métodos
        when(carDao.idExist(command1.getId())).thenReturn(false);
        when(carDao.idExist(command2.getId())).thenReturn(false);

        // Simulación de la creación de suministros
        when(carRepository.create(any(Car.class))).thenReturn(new Car());

        // Act
        List<Car> result = carCreateService.execute(List.of(command1, command2));

        // Assert
        assertNotNull(result);
        assertEquals(2, result.size());
        verify(supplyPublisher, times(2)).publishMessage(any());
    }

    @Test
    public void execute_ShouldPublishSupplyMessage_WhenSupplyIsCreated() {
        // Arrange
        CarCreateCommand command = new CarCreateCommand(1L,1, 10, "ESTATE" ,new BigDecimal(100.0));
        when(carDao.idExist(command.getId())).thenReturn(false);
        when(carRepository.create(any(Car.class))).thenReturn(new Car()); // Simulación correcta

        // Act
        carCreateService.execute(List.of(command));

        // Assert
        verify(supplyPublisher, times(1)).publishMessage(any());
    }
}
