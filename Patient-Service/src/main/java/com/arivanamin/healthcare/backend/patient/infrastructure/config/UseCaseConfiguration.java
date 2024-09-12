package com.arivanamin.healthcare.backend.patient.infrastructure.config;

import com.arivanamin.healthcare.backend.patient.domain.usecase.CreatePatientUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UseCaseConfiguration {
    
    // todo 9/12/24 - pass jpa repository as argument
    @Bean
    CreatePatientUseCase savePatientCommand () {
        return null;
    }
}
