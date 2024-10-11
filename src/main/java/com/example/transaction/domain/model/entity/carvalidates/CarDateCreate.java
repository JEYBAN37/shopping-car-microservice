package com.example.transaction.domain.model.entity.carvalidates;

import lombok.Getter;
import java.time.LocalDateTime;


@Getter
public class CarDateCreate {
    LocalDateTime date;

    private CarDateCreate(LocalDateTime  date) {
        this.date = date;
    }

    public static CarDateCreate of() {
        return new CarDateCreate(nowDate());
    }

    private static LocalDateTime nowDate() {
        return LocalDateTime.now();
    }
}
