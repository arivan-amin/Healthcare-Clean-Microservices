package com.arivanamin.healthcare.backend.patient.domain.usecase;

import com.arivanamin.healthcare.backend.patient.domain.entity.Patient;

import java.util.UUID;

@FunctionalInterface
public interface UpdatePatientUseCase {
    
    public Patient execute (UUID id, Patient patient);
}
