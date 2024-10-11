package com.example.transaction.domain.model.entity;

import com.example.transaction.domain.model.entity.carvalidates.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDateTime;
import java.util.List;



@Getter
@Setter
@NoArgsConstructor
public class Car {

    private CarIdUser idUser;
    private List<Article> articles;
    private CarDateUpdate dateUpdate;
    private CarDateCreate dateCreate;


    public Car(Long idUser, LocalDateTime dateUpdate, List<Article> articles){
        this.articles = articles;
        this.idUser = CarIdUser.of(idUser);
        this.dateUpdate = CarDateUpdate.of(dateUpdate);
    }

    public Car requestToCreate(Long id)
    {
        this.idUser = CarIdUser.of(id);
        this.dateCreate = CarDateCreate.of();
        return this;
    }

    public Long getIdUser() {return idUser.getIdUser();}
    public LocalDateTime getDateCreate() {return dateCreate.getDate();}
    public LocalDateTime getDateUpdate(){return dateUpdate.getDate();}
}

