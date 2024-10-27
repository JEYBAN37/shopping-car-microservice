package com.example.car.domain.port.repository;


import com.example.car.domain.model.entity.Car;

public interface CarRepository {
    Car create (Car request);
    Car update (Car request);
}
