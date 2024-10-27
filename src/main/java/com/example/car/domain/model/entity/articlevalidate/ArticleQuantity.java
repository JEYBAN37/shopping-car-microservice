package com.example.car.domain.model.entity.articlevalidate;
import com.example.car.domain.model.exception.CarException;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import static com.example.car.domain.model.constant.CarConstant.*;

@Getter
@Setter
@NoArgsConstructor
public class ArticleQuantity {
    Integer quantity;

    private ArticleQuantity (Integer quantity){
        this.quantity = quantity;
    }
    public static ArticleQuantity of (Integer quantity) {
        toValidQuantity(quantity);
        return new ArticleQuantity(quantity);
    }

    private static void toValidQuantity(Integer quantity) {
        if( quantity == null )
            throw new CarException(QUANTITY_MANDATORY);
        if (quantity < ZERO_CONSTANT)
            throw new CarException(QUANTITY_MESSAGE_MIN_ERROR);
    }
}
