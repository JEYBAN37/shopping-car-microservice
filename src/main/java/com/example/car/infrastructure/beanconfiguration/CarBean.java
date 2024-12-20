package com.example.car.infrastructure.beanconfiguration;

import com.example.car.application.interfaces.FeignInterceptorService;
import com.example.car.domain.port.dao.ArticleDao;
import com.example.car.domain.port.dao.CarDao;
import com.example.car.domain.port.repository.CarRepository;
import com.example.car.domain.port.services.ArticleService;
import com.example.car.domain.service.CarArticleDeleteService;
import com.example.car.domain.service.CarCreateService;

import com.example.car.domain.service.CarGetService;
import com.example.car.domain.service.CarUpdateService;
import com.example.car.infrastructure.adapter.http.HttpArticleService;
import com.example.car.infrastructure.adapter.http.TokenInterceptor;
import com.example.car.infrastructure.adapter.http.client.ArticleClient;
import com.example.car.infrastructure.adapter.http.client.FeignClientInterceptor;
import com.example.car.infrastructure.adapter.mapper.ArticleDboMapper;
import feign.Logger;
import feign.Request;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
@AllArgsConstructor
public class CarBean {

    @Bean
    public CarGetService carGetService(ArticleDao articleDao,ArticleService articleService) {
        return new CarGetService(articleDao,articleService);
    }

    @Bean
    public CarCreateService carCreateService(CarRepository carRepository, CarDao carDao) {
        return new CarCreateService(carRepository, carDao);
    }

    @Bean
    public CarUpdateService carUpdateService(CarRepository carRepository, CarDao carDao,
                                             ArticleService articleService) {
        return new CarUpdateService(carRepository, carDao, articleService);
    }

    @Bean
    public CarArticleDeleteService carArticleDeleteService(CarRepository carRepository, CarDao carDao) {
        return new CarArticleDeleteService(carRepository, carDao);
    }

    @Bean
    FeignInterceptorService feignInterceptorService(FeignClientInterceptor feignClientInterceptor){
        return new TokenInterceptor(feignClientInterceptor);
    }

    @Bean
    public ArticleService articleService(ArticleClient articleClient, ArticleDboMapper articleDboMapper) {
        return new HttpArticleService(articleClient,articleDboMapper);
    }

    @Bean
    public Logger.Level feignLoggerLevel() {
        return Logger.Level.FULL;
    }

    @Bean
    public Request.Options requestOptions() {
        return new Request.Options(5000, 10000);
    }

    @Bean
    public FeignClientInterceptor feignClientInterceptor() {
        return new FeignClientInterceptor(null);
    }
}
