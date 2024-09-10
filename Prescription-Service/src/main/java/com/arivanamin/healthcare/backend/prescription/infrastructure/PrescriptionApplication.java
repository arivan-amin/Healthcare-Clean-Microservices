package com.arivanamin.healthcare.backend.prescription.infrastructure;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication (scanBasePackages = "com.arivanamin.healthcare.backend")
@EnableDiscoveryClient
@EnableCaching
public class PrescriptionApplication {
    
    public static void main (String[] args) {
        SpringApplication.run(PrescriptionApplication.class, args);
    }
}
