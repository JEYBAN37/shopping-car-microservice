package com.example.transaction.infrastructure.adapter.jpa;
import com.example.transaction.infrastructure.adapter.entity.CarEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import java.util.Optional;


@Repository
public interface CarSpringJpaAdapterRepository extends JpaRepository<CarEntity, Long>, JpaSpecificationExecutor<CarEntity> {
    Optional<CarEntity> findById(Long id);
}