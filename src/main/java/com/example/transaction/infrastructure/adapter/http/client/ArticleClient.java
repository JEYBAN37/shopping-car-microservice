package com.example.transaction.infrastructure.adapter.http.client;


import com.example.transaction.infrastructure.adapter.json.ArticleJson;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.Optional;


@FeignClient(name = "user-microservice", url = "http://localhost:8081")
public interface ArticleClient {

    @GetMapping("/articles/exists")
    Optional<List<ArticleJson>> articles (List<Long>ids);

}
