package com.arivanamin.healthcare.backend.patient.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import static com.arivanamin.healthcare.backend.core.domain.base.CoreApplicationConfig.BASE_PACKAGE;

@SpringBootApplication (scanBasePackages = BASE_PACKAGE)
@EntityScan (BASE_PACKAGE)
@EnableJpaRepositories (basePackages = BASE_PACKAGE)
@EnableDiscoveryClient
@EnableCaching
public class PatientApplication {
    
    public static void main (String[] args) {
        SpringApplication.run(PatientApplication.class, args);
    }
}
