package com.example.car.domain.model.dto;

import com.example.car.domain.model.entity.articlevalidate.State;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ArticleDto {
    private Long id;
    private int quantity;
    private State state;
    private List<Long> categories;
}
