package com.example.car.infrastructure.rest.advice;

import com.example.car.domain.model.exception.CarException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import static com.example.car.domain.model.constant.CarConstant.*;

@ControllerAdvice
public class CarControllerAdvice {

    private Map<String, Object> buildResponseBody(String message, Object details) {
        Map<String, Object> body = new HashMap<>();
        body.put(TIMESTAMP, LocalDateTime.now());
        body.put(MESSAGE, message);
        body.put(DETAILS, details);
        return body;
    }

    @ExceptionHandler(CarException.class)
    public ResponseEntity<Object> handleSupplyException(CarException ex, WebRequest request) {
        Map<String, Object> body = buildResponseBody(ex.getErrorMessage(), request.getDescription(false));
        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }
}
