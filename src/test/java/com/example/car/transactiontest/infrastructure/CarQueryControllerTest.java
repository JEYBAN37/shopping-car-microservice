package com.example.car.transactiontest.infrastructure;

import com.example.car.application.query.CarGetArticles;
import com.example.car.domain.model.dto.ArticleToGetDto;
import com.example.car.infrastructure.rest.controller.CarQueryController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import java.util.Collections;
import java.util.List;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class CarQueryControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private CarGetArticles carGetArticles;

    @InjectMocks
    private CarQueryController carQueryController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(carQueryController).build();
    }

    @Test
    @WithMockUser // Simula un usuario autenticado
    public void testGetAllArticles() throws Exception {
        // Arrange
        List<ArticleToGetDto> articles = Collections.singletonList(new ArticleToGetDto(/* Inicializa los atributos necesarios */));
        when(carGetArticles.execute(ArgumentMatchers.anyString(), ArgumentMatchers.anyInt(), ArgumentMatchers.anyInt(),
                ArgumentMatchers.anyBoolean(), ArgumentMatchers.anyString(),
                ArgumentMatchers.anyString(), ArgumentMatchers.anyString()))
                .thenReturn(articles);

        // Act & Assert
        mockMvc.perform(get("/secure/car/articles/")
                        .header("Authorization", "Bearer token") // Ajusta según el tipo de token que utilices
                        .param("page", "0")
                        .param("size", "10")
                        .param("ascending", "true"))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser // Simula un usuario autenticado
    public void testGetAllArticles_NoDataFound() throws Exception {
        // Arrange
        when(carGetArticles.execute(ArgumentMatchers.anyString(), ArgumentMatchers.anyInt(), ArgumentMatchers.anyInt(),
                ArgumentMatchers.anyBoolean(), ArgumentMatchers.anyString(),
                ArgumentMatchers.anyString(), ArgumentMatchers.anyString()))
                .thenReturn(Collections.emptyList());

        // Act & Assert
        mockMvc.perform(get("/secure/car/articles/")
                        .header("Authorization", "Bearer token")
                        .param("page", "0")
                        .param("size", "10")
                        .param("ascending", "true"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isEmpty()); // Verifica que la respuesta está vacía
    }

    // Agrega más pruebas según sea necesario
}
