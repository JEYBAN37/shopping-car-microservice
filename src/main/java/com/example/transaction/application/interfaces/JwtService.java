package com.example.transaction.application.interfaces;


public interface JwtService {
    Long extractUserId(String token);
}
