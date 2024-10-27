package com.example.car.domain.model.entity.carvalidates;

import com.example.car.domain.model.exception.CarException;
import lombok.Getter;
import lombok.NoArgsConstructor;

import static com.example.car.domain.model.constant.CarConstant.*;

@Getter
@NoArgsConstructor
public class CarIdUser {

   Long idUser;
    private CarIdUser(Long idUser) {
        this.idUser = idUser;
    }
    public static CarIdUser of (Long idUser){
        toValidIdUser(idUser);
        return new CarIdUser(idUser);
    }
    private static void toValidIdUser(Long idUser) {
        if(idUser == null )
            throw new CarException(USER_MANDATORY);
        if (idUser <= ZERO_CONSTANT) {
            throw new CarException(USER_MESSAGE_MIN_ERROR);
        }
    }
}
