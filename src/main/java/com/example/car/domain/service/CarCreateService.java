package com.example.car.domain.service;
import com.example.car.domain.model.entity.Car;
import com.example.car.domain.model.exception.CarException;
import com.example.car.domain.port.dao.CarDao;
import com.example.car.domain.port.repository.CarRepository;
import lombok.AllArgsConstructor;

import static com.example.car.domain.model.constant.CarConstant.USER_EXIST;

@AllArgsConstructor
public class CarCreateService {
    private final CarRepository carRepository;
    private final CarDao carDao;

    public Car execute(Long user) {
        if (carDao.idExist(user))
            throw  new CarException(USER_EXIST);
        Car carToCreate = new Car().requestToCreate(user);
        return carRepository.create(carToCreate);
    }

}