package com.example.car.application.mapper;

import com.example.car.domain.model.dto.ArticleDto;
import com.example.car.domain.model.dto.ArticleToGetDto;
import com.example.car.domain.model.entity.Article;
import com.example.car.domain.model.entity.ArticleToGet;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ArticleDtoMapper {
    ArticleDto toDto (Article objectOfDomain);
    ArticleToGetDto toDtoJson (ArticleToGet objectOfDto);

}
