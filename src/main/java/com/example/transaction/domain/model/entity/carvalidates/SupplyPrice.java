package com.example.transaction.domain.model.entity.carvalidates;
import com.example.transaction.domain.model.exception.CarException;
import lombok.Getter;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;
import static com.example.transaction.domain.model.constant.CarConstant.*;

@NoArgsConstructor
@Getter
public class SupplyPrice {
    BigDecimal price;
    private SupplyPrice(BigDecimal price){
        this.price = price;
    }

    public static SupplyPrice of(BigDecimal price) {
        toValidPrice(price);
        return new SupplyPrice(price);
    }

    private static void toValidPrice(BigDecimal price) {
        if (price != null &&
                price.compareTo(BigDecimal.ZERO) < ZERO_CONSTANT) {
            throw new CarException(PRICE_MIN_ZERO);
        }
    }

}
