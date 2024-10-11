package com.example.transaction.domain.model.entity;

import com.example.transaction.domain.model.entity.articlevalidate.ArticleQuantity;
import com.example.transaction.domain.model.entity.articlevalidate.State;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Article {
    private Long id;
    private ArticleQuantity quantity;
    private State state;

    public Article (Long id, int quantity , State state){
        this.id = id;
        this.quantity = ArticleQuantity.of(quantity);
        this.state = state;

    }

    public int getQuantity (){return quantity.getQuantity();}
    public void setQuantity (int add){quantity.setQuantity(add);}

}
