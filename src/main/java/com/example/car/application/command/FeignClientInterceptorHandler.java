package com.example.car.application.command;

import com.example.car.application.interfaces.FeignInterceptorService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;


@AllArgsConstructor
@Component
public class FeignClientInterceptorHandler {
    FeignInterceptorService feignInterceptorService;

    public void sendToken (String token) {
        feignInterceptorService.injectToken(token);
    }
}
