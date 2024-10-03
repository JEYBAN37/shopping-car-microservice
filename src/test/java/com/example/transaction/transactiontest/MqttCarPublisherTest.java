package com.example.transaction.transactiontest;

import com.example.transaction.domain.model.dto.command.MessageCar;
import com.example.transaction.infrastructure.adapter.mqtt.MqttSupplyPublisher;
import com.example.transaction.infrastructure.beanconfiguration.CarBean;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import java.math.BigDecimal;
import static org.mockito.Mockito.*;

class MqttCarPublisherTest {

    @Mock
    private RabbitTemplate rabbitTemplate;

    @InjectMocks
    private MqttSupplyPublisher mqttSupplyPublisher;

    private MessageCar messageCar;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        // Configura un mensaje de prueba
        messageCar = new MessageCar(1, 10, new BigDecimal(99.99));
    }

    @Test
    void publishMessage_ShouldSendMessageToRabbitMQ() {
        // Act
        mqttSupplyPublisher.publishMessage(messageCar);

        // Assert
        verify(rabbitTemplate, times(1)).convertAndSend(
                eq(CarBean.EXCHANGE_NAME),  // Matcher para el primer parámetro
                eq("routing.key"),              // Matcher para el segundo parámetro
                any(String.class)               // Matcher para el tercer parámetro
        );
    }

    @Test
    void publishMessage_ShouldHandleException() {
        // Configura el comportamiento del mock para lanzar una excepción
        doThrow(new RuntimeException("Error")).when(rabbitTemplate).convertAndSend(anyString(), anyString(), anyString());

        // Act
        mqttSupplyPublisher.publishMessage(messageCar);

        // Assert
        verify(rabbitTemplate, times(1)).convertAndSend(anyString(), anyString(), anyString());
    }

    @Test
    void publishMessage_ShouldSerializeMessageSupplyToJson() throws Exception {
        // Act
        mqttSupplyPublisher.publishMessage(messageCar);

        // Assert
        ObjectMapper objectMapper = new ObjectMapper();
        String expectedJson = objectMapper.writeValueAsString(messageCar);
        verify(rabbitTemplate, times(1)).convertAndSend(eq(CarBean.EXCHANGE_NAME), eq("routing.key"), eq(expectedJson));
    }
}
