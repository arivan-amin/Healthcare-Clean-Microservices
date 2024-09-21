package com.arivanamin.healthcare.backend.patient.infrastructure.query;

import com.arivanamin.healthcare.backend.patient.domain.entity.Patient;
import com.arivanamin.healthcare.backend.patient.domain.persistence.PatientPersistence;
import com.arivanamin.healthcare.backend.patient.domain.query.ReadPatientByIdQuery;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
// todo 9/19/24 - should this implementation be in infrastructure or domain package ?
public class DefaultReadPatientByIdQuery implements ReadPatientByIdQuery {
    
    private final PatientPersistence persistence;
    
    @Override
    public Optional<Patient> execute (UUID id) {
        return persistence.getPatientById(id);
    }
}
