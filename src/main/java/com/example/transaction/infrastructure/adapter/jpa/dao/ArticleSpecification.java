package com.example.transaction.infrastructure.adapter.jpa.dao;


import com.example.transaction.infrastructure.adapter.entity.ArticleEntity;
import com.example.transaction.infrastructure.adapter.entity.CarEntity;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.Predicate;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;



@AllArgsConstructor
public class ArticleSpecification {
    private final Long byId;
    private final String byName;
    private final String byBrand;
    private final String byCategory;

    public Specification<ArticleEntity> toSpecification() {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (byId != null) {
                Join<ArticleEntity, CarEntity> carJoin = root.join("cars");
                predicates.add(
                        criteriaBuilder.equal(carJoin.get("idUser"), byId)
                );
            }
            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }

}