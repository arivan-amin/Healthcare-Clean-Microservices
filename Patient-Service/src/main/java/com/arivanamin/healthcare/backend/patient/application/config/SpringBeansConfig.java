package com.arivanamin.healthcare.backend.patient.application.config;

import com.arivanamin.healthcare.backend.patient.core.command.*;
import com.arivanamin.healthcare.backend.patient.core.persistence.PatientStorage;
import com.arivanamin.healthcare.backend.patient.core.query.ReadPatientByIdQuery;
import com.arivanamin.healthcare.backend.patient.core.query.ReadPatientsQuery;
import com.arivanamin.healthcare.backend.patient.storage.JpaPatientStorage;
import com.arivanamin.healthcare.backend.patient.storage.PatientRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
class SpringBeansConfig {
    
    @Bean
    public PatientStorage persistence (PatientRepository repository) {
        return new JpaPatientStorage(repository);
    }
    
    @Bean
    public ReadPatientsQuery readPatientsQuery (PatientStorage persistence) {
        return new ReadPatientsQuery(persistence);
    }
    
    @Bean
    public ReadPatientByIdQuery readPatientByIdQuery (PatientStorage persistence) {
        return new ReadPatientByIdQuery(persistence);
    }
    
    @Bean
    public CreatePatientCommand createPatientCommand (PatientStorage persistence) {
        return new CreatePatientCommand(persistence);
    }
    
    @Bean
    public UpdatePatientCommand updatePatientCommand (PatientStorage persistence) {
        return new UpdatePatientCommand(persistence);
    }
    
    @Bean
    public DeletePatientCommand deletePatientCommand (PatientStorage persistence) {
        return new DeletePatientCommand(persistence);
    }
}
