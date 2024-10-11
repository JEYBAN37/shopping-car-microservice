package com.example.transaction.domain.model.dto.command;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
@Setter
public class CarDeleteCommand {
    Long idArticle;
    private LocalDateTime dateUpdate;
}
