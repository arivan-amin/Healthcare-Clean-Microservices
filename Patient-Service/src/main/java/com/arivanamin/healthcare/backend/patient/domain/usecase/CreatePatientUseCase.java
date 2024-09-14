package com.arivanamin.healthcare.backend.patient.domain.usecase;

import com.arivanamin.healthcare.backend.patient.domain.entity.Patient;

@FunctionalInterface
public interface CreatePatientUseCase {
    
    public Patient execute (Patient patient);
}
