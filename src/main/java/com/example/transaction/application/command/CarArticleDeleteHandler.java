package com.example.transaction.application.command;

import com.example.transaction.domain.model.dto.command.CarDeleteCommand;
import com.example.transaction.domain.service.CarArticleDeleteService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;


@Component
@AllArgsConstructor
public class CarArticleDeleteHandler {
    private final CarArticleDeleteService carArticleDeleteService;
    private final JwtHandler jwtHandler;

    public void execute (CarDeleteCommand carDeleteCommand, String token){
        Integer userId = jwtHandler.getUserIdFromToken(token);
        carArticleDeleteService.execute(Long.valueOf(userId),carDeleteCommand);
    }

}
