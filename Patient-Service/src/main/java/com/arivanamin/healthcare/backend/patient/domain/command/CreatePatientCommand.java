package com.arivanamin.healthcare.backend.patient.domain.command;

import com.arivanamin.healthcare.backend.patient.domain.entity.Patient;

@FunctionalInterface
public interface CreatePatientCommand {
    
    public Patient execute (Patient patient);
}
