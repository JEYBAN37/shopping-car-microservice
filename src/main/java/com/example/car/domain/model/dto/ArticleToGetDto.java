package com.example.car.domain.model.dto;

import com.example.car.domain.model.entity.articlevalidate.State;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class ArticleToGetDto {
    private Long id;
    private String name;
    private String description;
    private int quantity;
    private String brand;
    private List<String> category;
    private String price;
    private State state;
}
