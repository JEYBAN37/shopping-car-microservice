package com.example.transaction.domain.model.entity.carvalidates;

import com.example.transaction.domain.model.exception.CarException;
import lombok.Getter;

import static com.example.transaction.domain.model.constant.CarConstant.*;

@Getter
public class SupplyIdArticle {

    Integer idArticle;
    private SupplyIdArticle(Integer idArticle) {
        this.idArticle = idArticle;
    }
    public static SupplyIdArticle of (Integer idArticle){
        toValidQuantity(idArticle);
        return new SupplyIdArticle(idArticle);
    }
    private static void toValidQuantity(Integer idArticle) {
        if(idArticle == null || String.valueOf(idArticle).isEmpty())
            throw new CarException(ARTICLE_MANDATORY);
        if (idArticle < ZERO_CONSTANT) {
            throw new CarException(ARTICLE_MESSAGE_MIN_ERROR);
        }
    }
}
