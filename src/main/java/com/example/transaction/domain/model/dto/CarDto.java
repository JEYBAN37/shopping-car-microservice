package com.example.transaction.domain.model.dto;

import com.example.transaction.domain.model.entity.Article;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDateTime;
import java.util.List;



@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CarDto {
    private Long idUser;
    private List<Article> articles;
    private LocalDateTime dateUpdate;
    private LocalDateTime dateCreate;
}
