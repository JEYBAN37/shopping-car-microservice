package com.example.car.transactiontest.application;
import com.example.car.application.command.CarArticleDeleteHandler;
import com.example.car.application.command.JwtHandler;
import com.example.car.domain.model.dto.command.CarDeleteCommand;
import com.example.car.domain.service.CarArticleDeleteService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class CarArticleDeleteHandlerTest {

    private CarArticleDeleteService carArticleDeleteService;
    private JwtHandler jwtHandler;
    private CarArticleDeleteHandler carArticleDeleteHandler;

    @BeforeEach
    void setUp() {
        carArticleDeleteService = mock(CarArticleDeleteService.class);
        jwtHandler = mock(JwtHandler.class);
        carArticleDeleteHandler = new CarArticleDeleteHandler(carArticleDeleteService, jwtHandler);
    }

    @Test
    void testExecuteDeleteCarArticleSuccess() {
        CarDeleteCommand carDeleteCommand = new CarDeleteCommand(1L, LocalDateTime.now());
        String token = "Bearer valid_token";
        when(jwtHandler.getUserIdFromToken(token)).thenReturn(Integer.valueOf("123"));

        carArticleDeleteHandler.execute(carDeleteCommand, token);

        verify(jwtHandler).getUserIdFromToken(token);
        verify(carArticleDeleteService).execute(123L, carDeleteCommand);
    }

    @Test
    void testExecuteDeleteCarArticleInvalidToken() {
        CarDeleteCommand carDeleteCommand = new CarDeleteCommand(1L, LocalDateTime.now());
        String token = "Bearer invalid_token";
        when(jwtHandler.getUserIdFromToken(token)).thenThrow(new RuntimeException("Invalid token"));

        assertThrows(RuntimeException.class, () -> {
            carArticleDeleteHandler.execute(carDeleteCommand, token);
        });

        verify(jwtHandler).getUserIdFromToken(token);
        verify(carArticleDeleteService, never()).execute(anyLong(), any());
    }
}
