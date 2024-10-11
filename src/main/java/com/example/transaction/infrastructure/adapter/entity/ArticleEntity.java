package com.example.transaction.infrastructure.adapter.entity;

import com.example.transaction.domain.model.entity.articlevalidate.State;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "article")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
@Setter
public class ArticleEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer quantity;
    @Enumerated(EnumType.STRING)
    private State state;
}
