package com.example.transaction.infrastructure.adapter.jpa.dao;
import com.example.transaction.domain.model.entity.Car;
import com.example.transaction.domain.port.dao.CarDao;
import com.example.transaction.infrastructure.adapter.entity.CarEntity;
import com.example.transaction.infrastructure.adapter.jpa.CarSpringJpaAdapterRepository;
import com.example.transaction.infrastructure.adapter.mapper.CarDboMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import java.sql.Timestamp;
import java.util.Optional;

@AllArgsConstructor
@Repository
public class CarH2Dao implements CarDao {

    private final CarSpringJpaAdapterRepository carSpringJpaAdapterRepository;
    private final CarDboMapper carDboMapper;

    @Override
    public Car getSupply(Long id) {
        Optional<CarEntity> optionalArticle = carSpringJpaAdapterRepository.findById(id);
        return optionalArticle.map(carDboMapper::toDomain).orElse(null);
    }

    @Override
    public boolean dateExist(Timestamp email) {
        return false;
    }

    @Override
    public boolean idExist(Long id) {
        return carSpringJpaAdapterRepository.existsById(id);
    }
}
