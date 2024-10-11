package com.example.transaction.domain.port.dao;
import com.example.transaction.domain.model.entity.Car;


public interface CarDao {
    Car getCar (Long id);
    boolean idExist (Long id);
}
