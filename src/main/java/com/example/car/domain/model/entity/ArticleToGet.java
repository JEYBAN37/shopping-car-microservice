package com.example.car.domain.model.entity;

import com.example.car.domain.model.entity.articlevalidate.State;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class ArticleToGet {
    private Long id;
    private String name;
    private String description;
    private int quantity;
    private String brand;
    private List<String> category;
    private BigDecimal price;
    private State state;
}
