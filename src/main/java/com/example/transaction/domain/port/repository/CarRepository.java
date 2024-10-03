package com.example.transaction.domain.port.repository;


import com.example.transaction.domain.model.entity.Car;

public interface CarRepository {
    Car create (Car request);
    Car update (Car request);
}
