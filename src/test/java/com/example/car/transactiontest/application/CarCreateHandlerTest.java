package com.example.car.transactiontest.application;

import com.example.car.application.command.CarCreateHandler;
import com.example.car.application.command.JwtHandler;
import com.example.car.application.mapper.CarDtoMapper;
import com.example.car.domain.model.dto.CarDto;
import com.example.car.domain.model.entity.Car;
import com.example.car.domain.service.CarCreateService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class CarCreateHandlerTest {

    private CarCreateService carCreateService;
    private CarDtoMapper carDtoMapper;
    private JwtHandler jwtHandler;
    private CarCreateHandler carCreateHandler;

    @BeforeEach
    void setUp() {
        carCreateService = mock(CarCreateService.class);
        carDtoMapper = mock(CarDtoMapper.class);
        jwtHandler = mock(JwtHandler.class);
        carCreateHandler = new CarCreateHandler(carCreateService, carDtoMapper, jwtHandler);
    }

    @Test
    void testExecuteCreateCarSuccess() {
        String token = "eyJhbGciOiJIUzI1NiJ9.eyJyb2xlcyI6WyJST0xFX0FETUlOIl0sImlkIjo4NSwic3ViIjoianVwdXNAZXhhbXBsZS5jb20iLCJpYXQiOjE3MzAwMzk2ODQsImV4cCI6MTczMDEyNjA4NH0.1hEj6FR5791ZKbF-AAjZmQ3gTTfdoRugMgoURHBJEB4";
        Long userId = 123L;
        Car car = new Car(); // Initialize with necessary fields
        CarDto carDto = new CarDto(); // Initialize as needed

        when(jwtHandler.getUserIdFromToken(token)).thenReturn(Integer.valueOf("123"));
        when(carCreateService.execute(userId)).thenReturn(car);
        when(carDtoMapper.toDto(car)).thenReturn(carDto);

        CarDto result = carCreateHandler.execute(token);

        assertNotNull(result);
        verify(jwtHandler).getUserIdFromToken(token);
        verify(carCreateService).execute(userId);
        verify(carDtoMapper).toDto(car);
    }

    @Test
    void testExecuteCreateCarInvalidToken() {
        String token = "invalid_token";
        when(jwtHandler.getUserIdFromToken(token)).thenThrow(new RuntimeException("Invalid token"));

        assertThrows(RuntimeException.class, () -> {
            carCreateHandler.execute(token);
        });

        verify(jwtHandler).getUserIdFromToken(token);
        verify(carCreateService, never()).execute(anyLong());
    }
}
