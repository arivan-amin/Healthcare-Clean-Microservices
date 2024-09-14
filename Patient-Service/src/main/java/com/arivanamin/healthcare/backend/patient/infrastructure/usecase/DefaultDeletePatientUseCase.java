package com.arivanamin.healthcare.backend.patient.infrastructure.usecase;

import com.arivanamin.healthcare.backend.patient.domain.persistence.PatientPersistence;
import com.arivanamin.healthcare.backend.patient.domain.usecase.DeletePatientUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class DefaultDeletePatientUseCase implements DeletePatientUseCase {
    
    private final PatientPersistence persistence;
    
    @Override
    public void execute (UUID id) {
        persistence.delete(id);
    }
}
