package com.example.transaction.domain.service;
import com.example.transaction.domain.model.entity.Car;
import com.example.transaction.domain.model.exception.CarException;
import com.example.transaction.domain.port.dao.CarDao;
import com.example.transaction.domain.port.repository.CarRepository;
import lombok.AllArgsConstructor;

import static com.example.transaction.domain.model.constant.CarConstant.USER_NO_EXIST;

@AllArgsConstructor
public class CarCreateService {
    private final CarRepository carRepository;
    private final CarDao carDao;

    public Car execute(Long user) {
        if (carDao.idExist(user))
            throw  new CarException(USER_NO_EXIST);
        Car carToCreate = new Car().requestToCreate(user);
        return carRepository.create(carToCreate);
    }

}