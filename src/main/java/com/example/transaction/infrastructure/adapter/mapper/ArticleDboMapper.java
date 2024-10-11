package com.example.transaction.infrastructure.adapter.mapper;

import com.example.transaction.domain.model.entity.Article;
import com.example.transaction.domain.model.entity.articlevalidate.State;
import com.example.transaction.infrastructure.adapter.json.ArticleJson;
import org.springframework.stereotype.Component;

import static com.example.transaction.domain.model.constant.CarConstant.ZERO_CONSTANT;

@Component
public class ArticleDboMapper {

    public Article toDomain(ArticleJson json){
        if(json == null){
            return null;
        }

        State state = State.AVAILABLE;

        if (json.getQuantity() == ZERO_CONSTANT)
            state = State.SPENT;

        return new Article(
                json.getId(),
                json.getQuantity(),
                state
        );
    }
}
