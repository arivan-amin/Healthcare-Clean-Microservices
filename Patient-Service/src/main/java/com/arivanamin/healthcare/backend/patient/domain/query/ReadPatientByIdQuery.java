package com.arivanamin.healthcare.backend.patient.domain.query;

import com.arivanamin.healthcare.backend.patient.domain.entity.Patient;
import com.arivanamin.healthcare.backend.patient.domain.persistence.PatientPersistence;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ReadPatientByIdQuery {
    
    private final PatientPersistence persistence;
    
    public Patient execute (UUID id) {
        return persistence.getPatientById(id).orElseThrow();
    }
}
