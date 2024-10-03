package com.example.transaction.domain.model.entity;

import com.example.transaction.domain.model.dto.command.CarCreateCommand;
import com.example.transaction.domain.model.entity.carvalidates.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;


@Getter
@Setter
@NoArgsConstructor
public class Car {
    private Long id;
    private SupplyIdArticle idArticle;
    private SupplyQuantity quantity;
    private SupplyState state;
    private SupplyPrice price;
    private SupplyDate date;

    public Car(Long id, Integer idArticle, int quantity, String state , BigDecimal price){
        this.id = id;
        this.idArticle = SupplyIdArticle.of(idArticle);
        this.quantity = SupplyQuantity.of(quantity);
        this.state = SupplyState.of(state);
        this.price = SupplyPrice.of(price);
        this.date = SupplyDate.of();
    }
    public Car requestToCreate(CarCreateCommand carCreateCommand)
    {
        this.idArticle = SupplyIdArticle.of(carCreateCommand.getIdArticle());
        this.quantity = SupplyQuantity.of(carCreateCommand.getQuantity());
        this.state = SupplyState.of(carCreateCommand.getState());
        this.date = SupplyDate.of();
        this.price = SupplyPrice.of(carCreateCommand.getPrice());
        return this;
    }

    public Integer getIdArticle() {
        return idArticle.getIdArticle();
    }
    public Integer getQuantity() {
        return quantity.getQuantity();
    }
    public String getState() {return state.getState();}
    public LocalDateTime getDate() {return date.getDate();}
    public BigDecimal getPrice (){return price.getPrice();}
}

