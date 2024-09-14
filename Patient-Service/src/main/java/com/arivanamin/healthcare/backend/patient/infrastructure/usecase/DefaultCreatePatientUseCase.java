package com.arivanamin.healthcare.backend.patient.infrastructure.usecase;

import com.arivanamin.healthcare.backend.patient.domain.entity.Patient;
import com.arivanamin.healthcare.backend.patient.domain.persistence.PatientPersistence;
import com.arivanamin.healthcare.backend.patient.domain.usecase.CreatePatientUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DefaultCreatePatientUseCase implements CreatePatientUseCase {
    
    private final PatientPersistence persistence;
    
    @Override
    public Patient execute (Patient patient) {
        return persistence.create(patient);
    }
}
