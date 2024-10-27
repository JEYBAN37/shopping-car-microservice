package com.example.car.domain.model.entity;

import com.example.car.domain.model.entity.articlevalidate.ArticleQuantity;
import com.example.car.domain.model.entity.articlevalidate.State;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Article {
    private Long id;
    private ArticleQuantity quantity;
    private Long brand;
    private State state;
    private List<Long> categories;

    public Article (Long id, int quantity , State state, List<Long> categories){
        this.id = id;
        this.quantity = ArticleQuantity.of(quantity);
        this.state = state;
        this.categories = categories;
    }

    public int getQuantity (){return quantity.getQuantity();}
    public void setQuantity (int add){quantity.setQuantity(add);}

}
