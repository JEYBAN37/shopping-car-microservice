package com.example.transaction.infrastructure.adapter.json;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class ArticleJson {
    private Long id;
    private int quantity;
    private List<Long> categories;
}
