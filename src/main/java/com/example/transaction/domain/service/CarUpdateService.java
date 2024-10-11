package com.example.transaction.domain.service;

import com.example.transaction.domain.model.dto.command.CarEditCommand;
import com.example.transaction.domain.model.entity.Article;
import com.example.transaction.domain.model.entity.Car;
import com.example.transaction.domain.model.exception.CarException;
import com.example.transaction.domain.port.services.ArticleService;
import com.example.transaction.domain.port.dao.CarDao;
import com.example.transaction.domain.port.repository.CarRepository;
import lombok.AllArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import static com.example.transaction.domain.model.constant.CarConstant.*;

@AllArgsConstructor
public class CarUpdateService {
    private final CarRepository carRepository;
    private final CarDao carDao;
    private final ArticleService articleService;

    public Car execute(CarEditCommand editCommand,Long id) {

       Car carUser = carDao.getCar(id);

       if(carUser == null)
            throw new CarException(CAR_NO_EXIST);

       List<Article> update = updateCarArticles(carUser.getArticles(),editCommand.getIdArticles());
       LocalDateTime updateDate = LocalDateTime.now();
       return carRepository.update(new Car(id,updateDate,update));
    }

    public List<Article> updateCarArticles(List<Article> articlesCar, Map<Long, Integer> articlesNewCar) {

        Map<Long, Article> currentArticlesMap = articlesCar.stream()
                .collect(Collectors.toMap(Article::getId, Function.identity()));

        List<Article> updatedArticles = new ArrayList<>();


        List<Long> newArticleIds = articlesNewCar.keySet().stream()
                .filter(articleId -> !currentArticlesMap.containsKey(articleId))
                .toList();

        List<Article> newArticlesFromStock = articleService.getArticlesByIds(newArticleIds);


        Map<Long, Article> newArticlesMap = newArticlesFromStock.stream()
                .collect(Collectors.toMap(Article::getId, Function.identity()));

        for (Map.Entry<Long, Integer> newArticleEntry : articlesNewCar.entrySet()) {
            Long articleId = newArticleEntry.getKey();
            int newQuantity = newArticleEntry.getValue();

            Article updatedArticle;

            if (currentArticlesMap.containsKey(articleId)) {

                Article currentArticle = currentArticlesMap.get(articleId);
                int totalQuantity = currentArticle.getQuantity() + newQuantity;

                validateStock(currentArticle, totalQuantity);

                currentArticle.setQuantity(totalQuantity);
                updatedArticle = currentArticle;
            } else {

                Article articleFromStock = newArticlesMap.get(articleId);
                if (articleFromStock == null) {
                    throw new CarException(ARTICLE_NO_EXIST);
                }

                validateStock(articleFromStock, newQuantity);

                updatedArticle = new Article(articleId, newQuantity, articleFromStock.getState());
            }

            updatedArticles.add(updatedArticle);
        }

        return updatedArticles;
    }

    private void validateStock(Article article, int requestedQuantity) {
        if (article.getQuantity() < requestedQuantity)
            throw new CarException(ARTICLE_NO_STOCK);

    }
}
