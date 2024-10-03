package com.example.transaction.transactiontest;

import com.example.transaction.application.command.CarCreateHandler;
import com.example.transaction.application.mapper.CarDtoMapper;
import com.example.transaction.domain.model.dto.CarDto;
import com.example.transaction.domain.model.dto.command.CarCreateCommand;
import com.example.transaction.domain.model.entity.Car;
import com.example.transaction.domain.service.CarCreateService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

 class CarCreateHandlerTest {

    @Mock
    private CarCreateService carCreateService;

    @Mock
    private CarDtoMapper carDtoMapper;

    @InjectMocks
    private CarCreateHandler carCreateHandler;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
     void execute_ShouldReturnSupplyDtoList_WhenCreateCommandsAreValid() {
        // Arrange
        CarCreateCommand command1 = new CarCreateCommand(1L,1, 10, "ESTATE" ,new BigDecimal(100.0));
        CarCreateCommand command2 = new CarCreateCommand(2L,2, 10, "ESTATE" ,new BigDecimal(100.0));
        Car car1 = new Car(); // Suponiendo que Car tiene un constructor vacío
        Car car2 = new Car();

        // Simulación de retorno de servicios
        when(carCreateService.execute(List.of(command1, command2))).thenReturn(List.of(car1, car2));
        when(carDtoMapper.toDto(car1)).thenReturn(new CarDto());
        when(carDtoMapper.toDto(car2)).thenReturn(new CarDto());

        // Act
        List<CarDto> result = carCreateHandler.execute(List.of(command1, command2));

        // Assert
        assertNotNull(result);
        assertEquals(2, result.size());
        verify(carCreateService, times(1)).execute(List.of(command1, command2));
        verify(carDtoMapper, times(2)).toDto(any(Car.class)); // Verificar que toDto fue llamado
    }

    @Test
     void execute_ShouldHandleEmptySupplyList() {
        // Arrange
        List<CarCreateCommand> createCommands = List.of();

        // Act
        List<CarDto> result = carCreateHandler.execute(createCommands);

        // Assert
        assertNotNull(result);
        assertTrue(result.isEmpty());
        verify(carCreateService, times(1)).execute(createCommands);
    }

}
