package com.arivanamin.healthcare.backend.patient.domain.usecase;

import java.util.UUID;

public interface DeletePatientUseCase {
    
    public void execute (UUID id);
}
