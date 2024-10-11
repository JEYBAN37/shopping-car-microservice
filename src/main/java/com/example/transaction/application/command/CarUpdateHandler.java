package com.example.transaction.application.command;

import com.example.transaction.application.mapper.CarDtoMapper;
import com.example.transaction.domain.model.dto.CarDto;
import com.example.transaction.domain.model.dto.command.CarEditCommand;
import com.example.transaction.domain.service.CarUpdateService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class CarUpdateHandler {
    private final CarUpdateService carUpdateService;
    private final CarDtoMapper carDtoMapper;
    private final JwtHandler jwtHandler;

    public CarDto execute (CarEditCommand editCommand, String token){
        Long id = jwtHandler.getUserIdFromToken(token);
        return carDtoMapper.toDto(carUpdateService.execute(editCommand, id));
    }
}