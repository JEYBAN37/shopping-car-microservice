package com.example.car.domain.model.exception;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serial;

@NoArgsConstructor(force = true)
@Getter
@Setter
public class CarException extends RuntimeException{
    @Serial
    private static final long serialVersionUID = 1L;
    private final String errorMessage;
    public CarException(String errorMessage){
        this.errorMessage = errorMessage;
    }
}
