package com.arivanamin.healthcare.backend.patient.domain.command;

import com.arivanamin.healthcare.backend.patient.domain.entity.Patient;
import com.arivanamin.healthcare.backend.patient.domain.persistence.PatientPersistence;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CreatePatientCommand {
    
    private final PatientPersistence persistence;
    
    public UUID execute (Patient patient) {
        return persistence.create(patient).getId();
    }
}
