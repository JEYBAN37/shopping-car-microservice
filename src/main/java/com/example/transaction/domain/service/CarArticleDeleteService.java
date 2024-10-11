package com.example.transaction.domain.service;

import com.example.transaction.domain.model.dto.command.CarDeleteCommand;
import com.example.transaction.domain.model.entity.Article;
import com.example.transaction.domain.model.entity.Car;
import com.example.transaction.domain.model.exception.CarException;
import com.example.transaction.domain.port.dao.CarDao;
import com.example.transaction.domain.port.repository.CarRepository;
import lombok.AllArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

import static com.example.transaction.domain.model.constant.CarConstant.CAR_NO_EXIST;
import static com.example.transaction.domain.model.constant.CarConstant.MESSAGE_ERROR_REMOVE;

@AllArgsConstructor
public class CarArticleDeleteService {
    private final CarRepository carRepository;
    private final CarDao carDao;

    public void execute(Long id, CarDeleteCommand carDeleteCommand){

        Car carUser = carDao.getCar(id);

        if(carUser == null)
            throw new CarException(CAR_NO_EXIST);

        carUser.getArticles().removeIf(article -> article.getId().
                equals(carDeleteCommand.getIdArticle()));


        boolean removed = carUser.getArticles().removeIf(article ->
                article.getId().equals(carDeleteCommand.getIdArticle()));
        if (!removed) {
            throw new CarException(MESSAGE_ERROR_REMOVE);
        }

        List<Article> update = carUser.getArticles();
        LocalDateTime updateDate = LocalDateTime.now();

        carRepository.update(new Car(id,updateDate,update));
    }
}
