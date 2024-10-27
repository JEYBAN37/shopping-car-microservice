package com.example.car.application.command;

import com.example.car.application.mapper.CarDtoMapper;
import com.example.car.domain.model.dto.CarDto;
import com.example.car.domain.model.dto.command.CarEditCommand;
import com.example.car.domain.service.CarUpdateService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class CarUpdateHandler {
    private final CarUpdateService carUpdateService;
    private final CarDtoMapper carDtoMapper;
    private final JwtHandler jwtHandler;
    private final FeignClientInterceptorHandler feignClientInterceptorHandler;


    public CarDto execute (CarEditCommand editCommand, String token){
        String cleanToken = token.replace("Bearer ", "").trim();
        Long id = Long.valueOf(jwtHandler.getUserIdFromToken(cleanToken));
        feignClientInterceptorHandler.sendToken(cleanToken);
        return carDtoMapper.toDto(carUpdateService.execute(editCommand, id));
    }
}
