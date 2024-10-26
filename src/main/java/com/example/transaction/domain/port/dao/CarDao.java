package com.example.transaction.domain.port.dao;
import com.example.transaction.domain.model.entity.Article;
import com.example.transaction.domain.model.entity.Car;

import java.util.List;


public interface CarDao {
    Car getCar (Long id);
    boolean idExist (Long id);
}
