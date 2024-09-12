package com.arivanamin.healthcare.backend.patient.domain.usecase;

import com.arivanamin.healthcare.backend.patient.domain.entity.Patient;

@FunctionalInterface
public interface UpdatePatientUseCase {
    
    public Patient execute (Patient patient);
}
