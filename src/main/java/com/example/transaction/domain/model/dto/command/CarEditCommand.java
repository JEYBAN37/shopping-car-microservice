package com.example.transaction.domain.model.dto.command;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class CarEditCommand {
    private Long idArticle;
    private int quantity;
    private String state;
}