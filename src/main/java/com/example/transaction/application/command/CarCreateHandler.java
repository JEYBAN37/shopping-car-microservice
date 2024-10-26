package com.example.transaction.application.command;

import com.example.transaction.application.mapper.CarDtoMapper;
import com.example.transaction.domain.model.dto.CarDto;
import com.example.transaction.domain.service.CarCreateService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;


@Component
@AllArgsConstructor
public class CarCreateHandler {
    private final CarCreateService carCreateService;
    private final CarDtoMapper carDtoMapper;
    private final JwtHandler jwtHandler;

    public CarDto execute (String token){
        String cleanToken = token.replace("Bearer ", "").trim();
        Long userId = Long.valueOf(jwtHandler.getUserIdFromToken(cleanToken));
        return carDtoMapper.toDto(carCreateService.execute(userId));
    }

}
