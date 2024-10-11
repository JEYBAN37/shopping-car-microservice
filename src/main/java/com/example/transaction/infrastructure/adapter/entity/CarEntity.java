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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idUser;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<ArticleEntity> articles;
    private LocalDateTime dateUpdate;
    private LocalDateTime dateCreate;
}
