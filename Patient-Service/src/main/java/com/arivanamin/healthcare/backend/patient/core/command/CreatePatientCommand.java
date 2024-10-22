package com.arivanamin.healthcare.backend.patient.core.command;

import com.arivanamin.healthcare.backend.patient.core.entity.Patient;
import com.arivanamin.healthcare.backend.patient.core.exception.PatientAlreadyExistsException;
import com.arivanamin.healthcare.backend.patient.core.persistence.PatientPersistence;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

@RequiredArgsConstructor
public class CreatePatientCommand {
    
    private final PatientPersistence persistence;
    
    public UUID execute (Patient patient) {
        if (doesPatientExist(patient)) {
            throw new PatientAlreadyExistsException();
        }
        return persistence.create(patient);
    }
    
    private boolean doesPatientExist (Patient patientToBeCreated) {
        return persistence.findAll()
            .stream()
            .anyMatch(patient -> patientToBeCreated.getEmail().equals(patient.getEmail()));
    }
}
