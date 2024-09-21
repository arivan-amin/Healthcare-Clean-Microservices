package com.arivanamin.healthcare.backend.patient.domain.command;

import java.util.UUID;

@FunctionalInterface
public interface DeletePatientCommand {
    
    public void execute (UUID id);
}
