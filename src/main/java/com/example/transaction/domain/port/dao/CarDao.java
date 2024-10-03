package com.example.transaction.domain.port.dao;



import com.example.transaction.domain.model.entity.Car;

import java.sql.Timestamp;


public interface CarDao {
    Car getSupply (Long id);
    boolean dateExist(Timestamp email);
    boolean idExist(Long id);
}
