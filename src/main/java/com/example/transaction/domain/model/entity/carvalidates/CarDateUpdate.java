package com.example.transaction.domain.model.entity.carvalidates;
import com.example.transaction.domain.model.exception.CarException;
import lombok.Getter;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;
import java.util.Objects;

import static com.example.transaction.domain.model.constant.CarConstant.MESSAGE_DATE_NOT_NULL;
import static com.example.transaction.domain.model.constant.CarConstant.MAX_DATE_CREATE;

@NoArgsConstructor
@Getter
public class CarDateUpdate {
    LocalDateTime date;
    private CarDateUpdate(LocalDateTime  date){
        this.date = date;
    }

    public static CarDateUpdate of(LocalDateTime  date) {
        toValidUpdateDate(date);
        return new CarDateUpdate(date);
    }

    private static void toValidUpdateDate(LocalDateTime date) {

        if (Objects.isNull(date))
            throw new CarException(MESSAGE_DATE_NOT_NULL);

        LocalDateTime creationDate = LocalDateTime.now();

        if (date.isBefore(creationDate))
            throw new CarException(MAX_DATE_CREATE);


    }

}
