package com.arivanamin.healthcare.backend.api.gateway.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
@EnableCaching
public class ApiGateway {
    
    public static void main (String[] args) {
        SpringApplication.run(ApiGateway.class, args);
    }
}
