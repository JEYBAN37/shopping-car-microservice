package com.example.car.transactiontest.application;

import com.example.car.application.command.CarUpdateHandler;
import com.example.car.application.command.FeignClientInterceptorHandler;
import com.example.car.application.command.JwtHandler;
import com.example.car.application.mapper.CarDtoMapper;
import com.example.car.domain.model.dto.CarDto;
import com.example.car.domain.model.dto.command.CarEditCommand;
import com.example.car.domain.model.entity.Car;
import com.example.car.domain.service.CarUpdateService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class CarUpdateHandlerTest {

    private CarUpdateService carUpdateService;
    private CarDtoMapper carDtoMapper;
    private JwtHandler jwtHandler;
    private FeignClientInterceptorHandler feignClientInterceptorHandler;
    private CarUpdateHandler carUpdateHandler;

    @BeforeEach
    void setUp() {
        carUpdateService = mock(CarUpdateService.class);
        carDtoMapper = mock(CarDtoMapper.class);
        jwtHandler = mock(JwtHandler.class);
        feignClientInterceptorHandler = mock(FeignClientInterceptorHandler.class);
        carUpdateHandler = new CarUpdateHandler(carUpdateService, carDtoMapper, jwtHandler, feignClientInterceptorHandler);
    }

    @Test
    void testExecuteUpdateCarSuccess() {
        CarEditCommand editCommand = new CarEditCommand(Map.of(1L, 2));
        String token = "eyJhbGciOiJIUzI1NiJ9.eyJyb2xlcyI6WyJST0xFX0FETUlOIl0sImlkIjo4NSwic3ViIjoianVwdXNAZXhhbXBsZS5jb20iLCJpYXQiOjE3MzAwMzk2ODQsImV4cCI6MTczMDEyNjA4NH0.1hEj6FR5791ZKbF-AAjZmQ3gTTfdoRugMgoURHBJEB4";
        Long userId = 123L;
        Car car = new Car(); // Initialize with necessary fields
        CarDto carDto = new CarDto(); // Initialize as needed

        when(jwtHandler.getUserIdFromToken(token)).thenReturn(Integer.valueOf("123"));
        when(carUpdateService.execute(editCommand, userId)).thenReturn(car);
        when(carDtoMapper.toDto(car)).thenReturn(carDto);

        CarDto result = carUpdateHandler.execute(editCommand, token);

        assertNotNull(result);
        verify(jwtHandler).getUserIdFromToken(token);
        verify(feignClientInterceptorHandler).sendToken(token);
        verify(carUpdateService).execute(editCommand, userId);
        verify(carDtoMapper).toDto(car);
    }

    @Test
    void testExecuteUpdateCarInvalidToken() {
        CarEditCommand editCommand = new CarEditCommand(Map.of(1L, 2));
        String token = "invalid_token";
        when(jwtHandler.getUserIdFromToken(token)).thenThrow(new RuntimeException("Invalid token"));

        assertThrows(RuntimeException.class, () -> {
            carUpdateHandler.execute(editCommand, token);
        });

        verify(jwtHandler).getUserIdFromToken(token);
        verify(carUpdateService, never()).execute(any(), anyLong());
    }
}
