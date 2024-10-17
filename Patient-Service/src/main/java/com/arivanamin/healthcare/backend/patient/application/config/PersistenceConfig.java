package com.arivanamin.healthcare.backend.patient.application.config;

import com.arivanamin.healthcare.backend.patient.core.persistence.PatientPersistence;
import com.arivanamin.healthcare.backend.patient.details.JpaPatientPersistence;
import com.arivanamin.healthcare.backend.patient.details.PatientRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class PersistenceConfig {
    
    @Bean
    public PatientPersistence persistence (PatientRepository repository) {
        return new JpaPatientPersistence(repository);
    }
}
