package com.arivanamin.healthcare.backend.patient.domain.command;

import com.arivanamin.healthcare.backend.patient.domain.entity.Patient;
import com.arivanamin.healthcare.backend.patient.domain.persistence.PatientPersistence;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreatePatientCommand {
    
    private final PatientPersistence persistence;
    
    public Patient execute (Patient patient) {
        return persistence.create(patient);
    }
}
