package com.example.transaction.infrastructure.beanconfiguration;

import com.example.transaction.domain.port.dao.CarDao;

import com.example.transaction.domain.port.repository.CarRepository;
import com.example.transaction.domain.service.CarCreateService;

import org.springframework.amqp.core.Queue;
import lombok.AllArgsConstructor;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
@AllArgsConstructor
public class CarBean {
    public static final String QUEUE_NAME = "tst";
    public static final String EXCHANGE_NAME = "test-exchange";

    @Bean
    public CarCreateService supplyCreateService(CarRepository carRepository, CarDao carDao) {
        return new CarCreateService(carRepository, carDao);
    }

    @Bean
    public Queue queue() {
        return new Queue(QUEUE_NAME, true);
    }

    @Bean
    public TopicExchange exchange() {
        return new TopicExchange(EXCHANGE_NAME);
    }

    @Bean
    public Jackson2JsonMessageConverter jsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        RabbitTemplate template = new RabbitTemplate(connectionFactory);
        template.setMessageConverter(jsonMessageConverter());
        return template;
    }
}
