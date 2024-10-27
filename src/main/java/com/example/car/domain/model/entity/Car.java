package com.example.car.domain.model.entity;

import com.example.car.domain.model.entity.carvalidates.*;
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

    // Constructor
    public Car(Long idUser, LocalDateTime dateUpdate, List<Article> articles,LocalDateTime dateCreate) {
        this.articles = articles;
        this.idUser = CarIdUser.of(idUser);
        this.dateCreate = new CarDateCreate(dateCreate);  // Solo se crea una vez
        this.dateUpdate = CarDateUpdate.of(dateUpdate, dateCreate);  // Valida la actualización
    }

    public Car requestToCreate(Long id) {
        this.idUser = CarIdUser.of(id);
        this.dateCreate = CarDateCreate.of();  // Fecha de creación solo una vez
        this.dateUpdate = CarDateUpdate.of(dateCreate.getDate().plusMinutes(1), dateCreate.getDate());  // Validación de fecha de actualización
        return this;
    }

    // Getters
    public Long getIdUser() { return idUser.getIdUser(); }
    public LocalDateTime getDateCreate() { return dateCreate.getDate(); }
    public LocalDateTime getDateUpdate() { return dateUpdate.getDate(); }

    public void updateDate(LocalDateTime newDate) {
        this.dateUpdate = CarDateUpdate.of(newDate, dateCreate.getDate());  // Se valida que la nueva fecha sea posterior a la de creación
    }
}

