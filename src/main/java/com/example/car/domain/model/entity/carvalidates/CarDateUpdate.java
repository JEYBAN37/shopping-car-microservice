package com.example.car.domain.model.entity.carvalidates;
import com.example.car.domain.model.exception.CarException;
import lombok.Getter;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;
import java.util.Objects;

import static com.example.car.domain.model.constant.CarConstant.MESSAGE_DATE_NOT_NULL;
import static com.example.car.domain.model.constant.CarConstant.MAX_DATE_CREATE;

@Getter
@NoArgsConstructor
public class CarDateUpdate {
    private LocalDateTime date;

    private CarDateUpdate(LocalDateTime date) {
        this.date = date;
    }

    public static CarDateUpdate of(LocalDateTime date, LocalDateTime creationDate) {
        toValidUpdateDate(date, creationDate);
        return new CarDateUpdate(date);
    }

    private static void toValidUpdateDate(LocalDateTime date, LocalDateTime creationDate) {
        if (Objects.isNull(date)) {
            throw new CarException(MESSAGE_DATE_NOT_NULL);
        }

        if (date.isBefore(creationDate)) {
            throw new CarException(MAX_DATE_CREATE);
        }
    }
}
