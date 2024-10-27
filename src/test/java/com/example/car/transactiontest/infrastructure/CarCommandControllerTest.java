package com.example.car.transactiontest.infrastructure;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import org.springframework.security.test.context.support.WithMockUser;
import com.example.car.application.command.CarArticleDeleteHandler;
import com.example.car.application.command.CarCreateHandler;
import com.example.car.application.command.CarUpdateHandler;
import com.example.car.domain.model.dto.CarDto;
import com.example.car.domain.model.dto.command.CarDeleteCommand;
import com.example.car.domain.model.dto.command.CarEditCommand;
import com.example.car.infrastructure.rest.controller.CarCommandController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalDateTime;

public class CarCommandControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private CarCreateHandler carCreateHandler;

    @Mock
    private CarUpdateHandler carUpdateHandler;

    @Mock
    private CarArticleDeleteHandler carArticleDeleteHandler;

    @InjectMocks
    private CarCommandController carCommandController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(carCommandController).build();
    }

    @Test
    @WithMockUser // Simula un usuario autenticado
    public void testAddCar() throws Exception {
        CarDto carDto = new CarDto(/* inicializa con datos de prueba */);
        String token = "Bearer valid-token"; // Simula un token válido

        when(carCreateHandler.execute(any())).thenReturn(carDto);

        mockMvc.perform(post("/secure/createCar/")
                        .header("Authorization", token)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        verify(carCreateHandler, times(1)).execute(any());
    }

    @Test
    @WithMockUser // Simula un usuario autenticado
    public void testUpdateCar() throws Exception {
        CarDto carDto = new CarDto(/* inicializa con datos de prueba */);
        CarEditCommand editCommand = new CarEditCommand(/* inicializa con datos de prueba */);
        String token = "Bearer valid-token"; // Simula un token válido

        when(carUpdateHandler.execute(any(), any())).thenReturn(carDto);

        mockMvc.perform(put("/secure/update")
                        .header("Authorization", token)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{ \"field\": \"value\" }")) // Asegúrate de usar un JSON válido para el cuerpo
                .andExpect(status().isOk());


        verify(carUpdateHandler, times(1)).execute(any(), any());
    }

    @Test
    @WithMockUser // Simula un usuario autenticado
    public void testDeleteCar() throws Exception {
        CarDeleteCommand deleteCommand = new CarDeleteCommand(1L, LocalDateTime.of(2024, 10, 27, 9, 45, 18, 718622600));
        String token = "Bearer valid-token"; // Simula un token válido
        doNothing().when(carArticleDeleteHandler).execute(any(), any());

        mockMvc.perform(delete("/secure/delete")
                        .header("Authorization", token)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{ \"field\": \"value\" }"))
                        .andExpect(status().isOk());

        verify(carArticleDeleteHandler, times(1)).execute(any(), any());
    }
}
