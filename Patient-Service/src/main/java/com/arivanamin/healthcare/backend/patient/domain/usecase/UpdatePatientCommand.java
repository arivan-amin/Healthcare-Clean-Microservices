package com.arivanamin.healthcare.backend.patient.domain.usecase;

import com.arivanamin.healthcare.backend.patient.domain.entity.Patient;

import java.util.UUID;

@FunctionalInterface
public interface UpdatePatientCommand {
    
    public Patient execute (UUID id, Patient patient);
}
