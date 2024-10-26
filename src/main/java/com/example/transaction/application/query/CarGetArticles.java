package com.example.transaction.application.query;

import com.example.transaction.application.command.FeignClientInterceptorHandler;
import com.example.transaction.application.command.JwtHandler;
import com.example.transaction.application.mapper.ArticleDtoMapper;
import com.example.transaction.domain.model.dto.ArticleToGetDto;
import com.example.transaction.domain.service.CarGetService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@AllArgsConstructor
@Component
public class CarGetArticles {
    private final CarGetService carGetService;
    private final ArticleDtoMapper articleDtoMapper;
    private final JwtHandler jwtHandler;
    private final FeignClientInterceptorHandler feignClientInterceptorHandler;
    public List<ArticleToGetDto> execute (String token,
                                          Integer page,
                                          Integer size,
                                          Boolean ascending,
                                          String byName,
                                          String byBrand,
                                          String byCategory){
        String cleanToken = token.replace("Bearer ", "").trim();
        Long userId = Long.valueOf(jwtHandler.getUserIdFromToken(cleanToken));
        feignClientInterceptorHandler.sendToken(cleanToken);
        return carGetService.execute(
                        userId,
                        page,
                        size,
                        ascending,
                        byBrand,
                        byName,
                        byCategory)
                .stream()
                .map(articleDtoMapper::toDtoJson)
                .toList();
    }
}
