package com.example.transaction.infrastructure.adapter.http.client;


import com.example.transaction.infrastructure.adapter.json.ArticleJson;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;


@FeignClient(name = "user-microservice", url = "http://localhost:8085")
public interface ArticleClient {

    @PostMapping("/secure/car/articles/")
    Optional<List<ArticleJson>> articles(@RequestBody List<Long> ids,
                                         @RequestParam("page") int page,
                                         @RequestParam("size") int size,
                                         @RequestParam("ascending") boolean ascending,
                                         @RequestParam(value = "byBrand",required = false) String byBrand,
                                         @RequestParam(value = "byName",required = false) String byName,
                                         @RequestParam(value = "byCategory",required = false) String byCategory
    );
}
