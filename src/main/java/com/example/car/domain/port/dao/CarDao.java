package com.example.car.domain.port.dao;
import com.example.car.domain.model.entity.Car;


public interface CarDao {
    Car getCar (Long id);
    boolean idExist (Long id);
}
