package com.example.transaction.application.mapper;

import com.example.transaction.domain.model.dto.ArticleDto;
import com.example.transaction.domain.model.dto.ArticleToGetDto;
import com.example.transaction.domain.model.entity.Article;
import com.example.transaction.domain.model.entity.ArticleToGet;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ArticleDtoMapper {
    ArticleDto toDto (Article objectOfDomain);
    ArticleToGetDto toDtoJson (ArticleToGet objectOfDto);

}
