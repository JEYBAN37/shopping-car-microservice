package com.example.transaction.infrastructure.rest.controller;

import com.example.transaction.application.query.CarGetArticles;
import com.example.transaction.domain.model.dto.ArticleDto;
import com.example.transaction.domain.model.dto.ArticleToGetDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/secure/")
public class CarQueryController {
    private final CarGetArticles carGetArticles;
    @Operation(summary = "Get All Articles")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Articles returned",
                    content = @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = ArticleDto.class)))),
            @ApiResponse(responseCode = "404", description = "No data found", content = @Content),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content)
    })
    @GetMapping("car/articles/")
    public List<ArticleToGetDto> getAll(@RequestHeader("Authorization") String token,
                                        @RequestParam() int page,
                                        @RequestParam() int size,
                                        @RequestParam() boolean ascending,
                                        @RequestParam(required = false) String byName,
                                        @RequestParam(required = false) String byBrand,
                                        @RequestParam(required = false) String byCategory
    ) {
        return carGetArticles.execute(
                                    token,
                                    page,
                                    size,
                                    ascending,
                                    byName,
                                    byBrand,
                                    byCategory
        );
    }
}
