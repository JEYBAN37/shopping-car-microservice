package com.example.transaction.transactiontest;

import com.example.transaction.application.command.CarCreateHandler;
import com.example.transaction.infrastructure.rest.controller.CarCommandController;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;



class CarCommandControllerTest {

    @InjectMocks
    private CarCommandController carCommandController;

    @Mock
    private CarCreateHandler carCreateHandler;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(carCommandController).build();
    }

}
