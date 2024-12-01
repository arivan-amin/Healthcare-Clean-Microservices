package com.arivanamin.healthcare.backend.patient.application.config;

import com.arivanamin.healthcare.backend.patient.core.command.*;
import com.arivanamin.healthcare.backend.patient.core.persistence.PatientPersistence;
import com.arivanamin.healthcare.backend.patient.core.query.ReadPatientByIdQuery;
import com.arivanamin.healthcare.backend.patient.core.query.ReadPatientsQuery;
import com.arivanamin.healthcare.backend.patient.details.JpaPatientPersistence;
import com.arivanamin.healthcare.backend.patient.details.PatientRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
class SpringBeansConfig {
    
    @Bean
    public PatientPersistence persistence (PatientRepository repository) {
        return new JpaPatientPersistence(repository);
    }
    
    @Bean
    public ReadPatientsQuery readPatientsQuery (PatientPersistence persistence) {
        return new ReadPatientsQuery(persistence);
    }
    
    @Bean
    public ReadPatientByIdQuery readPatientByIdQuery (PatientPersistence persistence) {
        return new ReadPatientByIdQuery(persistence);
    }
    
    @Bean
    public CreatePatientCommand createPatientCommand (PatientPersistence persistence) {
        return new CreatePatientCommand(persistence);
    }
    
    @Bean
    public UpdatePatientCommand updatePatientCommand (PatientPersistence persistence) {
        return new UpdatePatientCommand(persistence);
    }
    
    @Bean
    public DeletePatientCommand deletePatientCommand (PatientPersistence persistence) {
        return new DeletePatientCommand(persistence);
    }
}
