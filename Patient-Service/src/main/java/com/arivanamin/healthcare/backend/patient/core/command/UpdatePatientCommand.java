package com.arivanamin.healthcare.backend.patient.core.command;

import com.arivanamin.healthcare.backend.patient.core.entity.Patient;
import com.arivanamin.healthcare.backend.patient.core.persistence.PatientStorage;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UpdatePatientCommand {
    
    private final PatientStorage persistence;
    
    public void execute (Patient patient) {
        persistence.update(patient);
    }
}
