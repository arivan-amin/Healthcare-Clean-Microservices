package com.arivanamin.healthcare.backend.patient.core.command;

import com.arivanamin.healthcare.backend.patient.core.entity.Patient;
import com.arivanamin.healthcare.backend.patient.core.exception.PatientAlreadyExistsException;
import com.arivanamin.healthcare.backend.patient.core.persistence.PatientStorage;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

@RequiredArgsConstructor
public class CreatePatientCommand {
    
    private final PatientStorage persistence;
    
    public UUID execute (Patient patient) {
        if (doesPatientExist(patient)) {
            throw new PatientAlreadyExistsException();
        }
        return persistence.create(patient);
    }
    
    private boolean doesPatientExist (Patient patient) {
        return persistence.findAll()
            .stream()
            .anyMatch(p -> checkEmailDuplication(patient, p));
    }
    
    private static boolean checkEmailDuplication (Patient patientToBeCreated,
                                                  Patient existingPatient) {
        return patientToBeCreated.getEmail()
            .equals(existingPatient.getEmail());
    }
}
