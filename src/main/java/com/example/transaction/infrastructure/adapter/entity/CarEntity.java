package com.example.transaction.infrastructure.adapter.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "car")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
@Setter
public class CarEntity {
    @Id
    private Long idUser;
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "car_article",  // Nombre de la tabla intermedia
            joinColumns = @JoinColumn(name = "car_id"),
            inverseJoinColumns = @JoinColumn(name = "article_id")
    )
    private List<ArticleEntity> articles;
    private LocalDateTime dateUpdate;
    private LocalDateTime dateCreate;
}
