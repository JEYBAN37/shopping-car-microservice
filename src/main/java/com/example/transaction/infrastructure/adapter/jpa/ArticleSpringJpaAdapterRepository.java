package com.example.transaction.infrastructure.adapter.jpa;

import com.example.transaction.infrastructure.adapter.entity.ArticleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArticleSpringJpaAdapterRepository extends JpaRepository<ArticleEntity, Long>,
        JpaSpecificationExecutor<ArticleEntity> {
    @Query("SELECT a FROM ArticleEntity a JOIN a.cars c WHERE c.id = :carId")
    List<ArticleEntity> findAllByCarId(@Param("carId") Long carId);
}
