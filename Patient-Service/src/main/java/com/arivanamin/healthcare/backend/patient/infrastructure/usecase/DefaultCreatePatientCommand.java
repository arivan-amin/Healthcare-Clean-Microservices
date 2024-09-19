package com.arivanamin.healthcare.backend.patient.infrastructure.usecase;

import com.arivanamin.healthcare.backend.patient.domain.entity.Patient;
import com.arivanamin.healthcare.backend.patient.domain.persistence.PatientPersistence;
import com.arivanamin.healthcare.backend.patient.domain.usecase.CreatePatientCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DefaultCreatePatientCommand implements CreatePatientCommand {
    
    private final PatientPersistence persistence;
    
    @Override
    public Patient execute (Patient patient) {
        return persistence.create(patient);
    }
}
