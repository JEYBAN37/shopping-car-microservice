package com.example.transaction.infrastructure.rest.controller;

import com.example.transaction.application.command.CarCreateHandler;
import com.example.transaction.domain.model.dto.CarDto;
import com.example.transaction.domain.model.dto.command.CarCreateCommand;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/")
@AllArgsConstructor
@Tag(name ="Article Command Controller")
public class CarCommandController {
    private final CarCreateHandler carCreateHandler;

    @Operation(summary = "Create Supplies Aux Bodega")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Car created", content = @Content),
            @ApiResponse(responseCode = "409", content = @Content)
    })
    @PostMapping("addSupply/")
    public List<CarDto> addSupply (@RequestBody  List<CarCreateCommand>  createCommand){
        return carCreateHandler.execute(createCommand);
    }
}
