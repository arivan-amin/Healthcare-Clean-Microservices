package com.arivanamin.healthcare.backend.patient.domain.query;

import com.arivanamin.healthcare.backend.patient.domain.persistence.PatientPersistence;
import com.arivanamin.healthcare.backend.patient.domain.query.response.PatientResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ReadPatientByIdQuery {
    
    private final PatientPersistence persistence;
    
    public PatientResponse execute (UUID id) {
        return null;
    }
}
