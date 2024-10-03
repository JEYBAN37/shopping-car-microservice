package com.example.transaction.application.command;

import com.example.transaction.application.mapper.CarDtoMapper;
import com.example.transaction.domain.model.dto.CarDto;
import com.example.transaction.domain.model.dto.command.CarCreateCommand;
import com.example.transaction.domain.service.CarCreateService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class CarCreateHandler {
    private final CarCreateService carCreateService;
    private final CarDtoMapper carDtoMapper;

    public List<CarDto> execute (List<CarCreateCommand> createCommand){
        return carCreateService.execute(createCommand).stream()
                .map(carDtoMapper::toDto)
                .toList();
    }

}
