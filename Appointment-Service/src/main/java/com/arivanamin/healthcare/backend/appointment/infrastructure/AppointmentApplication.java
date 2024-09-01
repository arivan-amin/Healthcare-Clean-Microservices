package com.arivanamin.healthcare.backend.appointment.infrastructure;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
@EnableCaching
public class AppointmentApplication {
    
    // todo 9/1/24 - inject micrometer into controllers
    public static void main (String[] args) {
        SpringApplication.run(AppointmentApplication.class, args);
    }
}
