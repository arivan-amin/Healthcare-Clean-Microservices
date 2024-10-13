package com.arivanamin.healthcare.backend.patient.core.query;

import com.arivanamin.healthcare.backend.patient.core.entity.Patient;
import com.arivanamin.healthcare.backend.patient.core.exception.PatientNotFoundException;
import com.arivanamin.healthcare.backend.patient.core.persistence.PatientPersistence;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ReadPatientByIdQuery {
    
    private final PatientPersistence persistence;
    
    public Patient execute (UUID id) {
        return persistence.findById(id).orElseThrow(PatientNotFoundException::new);
    }
}
