package com.example.car.domain.model.dto.command;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.Map;


@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class CarEditCommand {
    private Map<Long,Integer> idArticles;
}