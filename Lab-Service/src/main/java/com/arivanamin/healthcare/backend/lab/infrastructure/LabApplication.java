package com.arivanamin.healthcare.backend.lab.infrastructure;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
@EnableCaching
public class LabApplication {
    
    public static void main (String[] args) {
        SpringApplication.run(LabApplication.class, args);
    }
}
