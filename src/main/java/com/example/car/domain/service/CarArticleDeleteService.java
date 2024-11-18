package com.example.car.domain.service;

import com.example.car.domain.model.dto.command.CarDeleteCommand;
import com.example.car.domain.model.entity.Article;
import com.example.car.domain.model.entity.Car;
import com.example.car.domain.model.exception.CarException;
import com.example.car.domain.port.dao.CarDao;
import com.example.car.domain.port.repository.CarRepository;
import lombok.AllArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static com.example.car.domain.model.constant.CarConstant.CAR_NO_EXIST;
import static com.example.car.domain.model.constant.CarConstant.MESSAGE_ERROR_REMOVE;

@AllArgsConstructor
public class CarArticleDeleteService {
    private final CarRepository carRepository;
    private final CarDao carDao;

    public void execute(Long id, CarDeleteCommand carDeleteCommand) {

        Car carUser = carDao.getCar(id);

        if (carUser == null) {
            throw new CarException(CAR_NO_EXIST);
        }

        // Create a mutable copy of the articles list
        List<Article> mutableArticles = new ArrayList<>(carUser.getArticles());

        // Find the article to be removed
        Article articleToRemove = mutableArticles.stream()
                .filter(article -> article.getId().equals(carDeleteCommand.getIdArticle()))
                .findFirst()
                .orElse(null);

        if (articleToRemove == null) {
            throw new CarException(MESSAGE_ERROR_REMOVE);
        }

        // Remove the article from the mutable list
        mutableArticles.remove(articleToRemove);

        // Update the car with the modified articles list and update date
        LocalDateTime updateDate = LocalDateTime.now();
        carUser.setArticles(mutableArticles);  // Assuming Car has a setArticles method
        carUser.updateDate(updateDate);     // Assuming Car has a setUpdateDate method

        carRepository.update(carUser);
    }


}
