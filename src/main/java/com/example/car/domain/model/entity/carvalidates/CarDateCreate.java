package com.example.car.domain.model.entity.carvalidates;

import lombok.Getter;
import java.time.LocalDateTime;


@Getter
public class CarDateCreate {
    LocalDateTime date;

    public CarDateCreate(LocalDateTime  date) {
        this.date = date;
    }

    public static CarDateCreate of() {
        return new CarDateCreate(nowDate());
    }


    private static LocalDateTime nowDate() {
        return LocalDateTime.now();
    }
}
