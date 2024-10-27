package com.example.car.infrastructure.adapter.jpa;
import com.example.car.infrastructure.adapter.entity.CarEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;


@Repository
public interface CarSpringJpaAdapterRepository extends JpaRepository<CarEntity, Long>,
        JpaSpecificationExecutor<CarEntity> {
}