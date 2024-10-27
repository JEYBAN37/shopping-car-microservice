package com.example.car.infrastructure.adapter.jpa.respository;
import com.example.car.domain.model.entity.Car;
import com.example.car.domain.port.repository.CarRepository;
import com.example.car.infrastructure.adapter.entity.CarEntity;
import com.example.car.infrastructure.adapter.jpa.CarSpringJpaAdapterRepository;
import com.example.car.infrastructure.adapter.mapper.CarDboMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
@AllArgsConstructor
public class CarH2Repository implements CarRepository {
    private final CarSpringJpaAdapterRepository carSpringJpaAdapterRepository;
    private final CarDboMapper carDboMapper;

    @Override
    public Car create(Car request) {
        CarEntity userToSave = carDboMapper.toDatabase(request);
        CarEntity userSaved = carSpringJpaAdapterRepository.save(userToSave);
        return carDboMapper.toDomain(userSaved);
    }

    @Override
    public Car update(Car request) {
        CarEntity userToUpdate = carDboMapper.toDatabase(request);
        CarEntity userUpdate = carSpringJpaAdapterRepository.save(userToUpdate);
        return carDboMapper.toDomain(userUpdate);
    }
}
