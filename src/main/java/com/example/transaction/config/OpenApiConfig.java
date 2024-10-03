package com.example.transaction.config;


import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
        info = @Info(
                title = "Microservice Car",
                description = "This is Api Microservice Car Second Module"
        )
)
public class OpenApiConfig {
}
