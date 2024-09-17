package com.arivanamin.healthcare.backend.patient.infrastructure.usecase;

import com.arivanamin.healthcare.backend.patient.domain.entity.Patient;
import com.arivanamin.healthcare.backend.patient.domain.persistence.PatientPersistence;
import com.arivanamin.healthcare.backend.patient.domain.usecase.UpdatePatientUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class DefaultUpdatePatientUseCase implements UpdatePatientUseCase {
    
    private final PatientPersistence persistence;
    
    @Override
    public Patient execute (UUID id, Patient patient) {
        return persistence.update(id, patient);
    }
}
