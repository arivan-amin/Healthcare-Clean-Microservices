package com.arivanamin.healthcare.backend.patient.core.command;

import com.arivanamin.healthcare.backend.patient.core.persistence.PatientStorage;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

@RequiredArgsConstructor
public class DeletePatientCommand {
    
    private final PatientStorage persistence;
    
    public void execute (UUID id) {
        persistence.delete(id);
    }
}
