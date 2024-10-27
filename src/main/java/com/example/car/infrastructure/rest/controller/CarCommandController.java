package com.example.car.infrastructure.rest.controller;

import com.example.car.application.command.CarArticleDeleteHandler;
import com.example.car.application.command.CarCreateHandler;
import com.example.car.application.command.CarUpdateHandler;
import com.example.car.domain.model.dto.CarDto;
import com.example.car.domain.model.dto.command.CarDeleteCommand;
import com.example.car.domain.model.dto.command.CarEditCommand;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/secure/")
@AllArgsConstructor
@Tag(name ="Car Command Controller")
public class CarCommandController {
    private final CarArticleDeleteHandler carArticleDeleteHandler;
    private final CarCreateHandler carCreateHandler;
    private final CarUpdateHandler carUpdateHandler;

    @Operation(summary = "Create Car")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Car created", content = @Content),
            @ApiResponse(responseCode = "409", content = @Content)
    })
    @PostMapping("createCar/")
    public CarDto addCar (@RequestHeader("Authorization") String token){
        return carCreateHandler.execute(token);
    }

    @Operation(summary = "Update an existing Brand")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Brand updated", content = @Content),
            @ApiResponse(responseCode = "404", description = "Brand not found", content = @Content)
    })
    @PutMapping("/update")
    public CarDto addArticleToCar (@RequestBody CarEditCommand editCommand, @RequestHeader("Authorization") String token){
        return carUpdateHandler.execute(editCommand, token);
    }

    @Operation(summary = "Delete a category by their id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Category deleted", content = @Content),
            @ApiResponse(responseCode = "404", description = "Category not found", content = @Content)
    })

    @DeleteMapping("/delete")
    public void deleteById(@RequestBody CarDeleteCommand carDeleteCommand, @RequestHeader("Authorization") String token){
        carArticleDeleteHandler.execute(carDeleteCommand,token);
    }
}
