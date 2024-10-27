package com.example.car.domain.model.dto.command;

import lombok.*;


@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class CarCreateCommand {
    private Integer quantity;
}
