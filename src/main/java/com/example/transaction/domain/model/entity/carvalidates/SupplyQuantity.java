package com.example.transaction.domain.model.entity.carvalidates;

import com.example.transaction.domain.model.exception.CarException;
import lombok.Getter;
import lombok.NoArgsConstructor;

import static com.example.transaction.domain.model.constant.CarConstant.*;

@Getter
@NoArgsConstructor
public class SupplyQuantity {

    Integer quantity;
    private SupplyQuantity(int quantity) {
        this.quantity = quantity;
    }
    public static SupplyQuantity of (Integer quantity){
        toValidQuantity(quantity);
        return new SupplyQuantity(quantity);
    }
    private static void toValidQuantity(Integer quantity) {
        if(quantity == null )
            throw new CarException(QUANTITY_MANDATORY);
        if (quantity <= ZERO_CONSTANT) {
            throw new CarException(QUANTITY_MESSAGE_MIN_ERROR);
        }
    }
}
