package com.arivanamin.healthcare.backend.radiology;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
@EnableCaching
public class RadiologyApplication {
    
    public static void main (String[] args) {
        SpringApplication.run(RadiologyApplication.class, args);
    }
}
