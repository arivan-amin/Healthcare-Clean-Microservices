package com.arivanamin.healthcare.backend.patient.infrastructure.usecase;

import com.arivanamin.healthcare.backend.patient.domain.entity.Patient;
import com.arivanamin.healthcare.backend.patient.domain.persistence.PatientPersistence;
import com.arivanamin.healthcare.backend.patient.domain.usecase.ReadPatientQuery;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
// todo 9/19/24 - should this implementation be in infrastructure or domain package ?
public class DefaultReadPatientQuery implements ReadPatientQuery {
    
    private final PatientPersistence persistence;
    
    @Override
    public List<Patient> execute () {
        return persistence.getAllPatients();
    }
}
