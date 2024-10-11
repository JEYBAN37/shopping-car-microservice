package com.example.transaction.domain.model.entity.carvalidates;
import com.example.transaction.domain.model.exception.CarException;
import static com.example.transaction.domain.model.constant.CarConstant.*;
import lombok.Getter;


import java.util.Map;

@Getter
public class CarIdArticle {


    private Map<Integer, String> idArticles;

    private CarIdArticle(Map<Integer, String> idArticles) {
        this.idArticles = idArticles;
    }

    public static CarIdArticle of(Map<Integer, String> idArticles) {
        toValidIdArticle(idArticles);
        return new CarIdArticle(idArticles);
    }


    private static void toValidIdArticle(Map<Integer, String> idArticles) {
        if (idArticles == null || idArticles.isEmpty()) {
            throw new CarException(ARTICLES_NOT_EMPTY);
        }

        for (Map.Entry<Integer, String> entry : idArticles.entrySet()) {
            Integer id = entry.getKey();
            String quantity = entry.getValue();


            if (id == null || id <= 0) {
                throw new CarException(INVALID_ID_MESSAGE + ": " + id);
            }


            if (quantity == null) {
                throw new CarException(QUANTITY_MANDATORY + ARTICLE_ID + id);
            }


            if (quantity.equals("0")) {
                throw new CarException(QUANTITY_MESSAGE_MIN_ERROR + ARTICLE_ID + id);
            }
        }
    }
}

