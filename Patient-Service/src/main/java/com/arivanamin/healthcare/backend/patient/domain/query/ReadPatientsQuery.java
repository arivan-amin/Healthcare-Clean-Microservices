package com.arivanamin.healthcare.backend.patient.domain.query;

import com.arivanamin.healthcare.backend.patient.domain.persistence.PatientPersistence;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReadPatientsQuery {
    
    private final PatientPersistence persistence;
    
    public ReadPatientsResponse execute () {
        return new ReadPatientsResponse(persistence.getAllPatients());
    }
}
