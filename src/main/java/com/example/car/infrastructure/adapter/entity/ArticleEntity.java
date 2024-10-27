package com.example.car.infrastructure.adapter.entity;

import com.example.car.domain.model.entity.articlevalidate.State;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "article")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
@Setter
public class ArticleEntity {
    @Id
    private Long id;
    private Integer quantity;
    @Enumerated(EnumType.STRING)
    private State state;
    private Long brand;
    @ElementCollection
    private List<Long> categories;
    @ManyToMany(mappedBy = "articles")
    private List<CarEntity> cars;

    public ArticleEntity(Long id, Integer quantity, State state, List<Long> categories) {
        this.id = id;
        this.quantity = quantity;
        this.state = state;
        this.categories = categories;
    }

}
