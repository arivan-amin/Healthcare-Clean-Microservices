package com.arivanamin.healthcare.backend.patient.core.query;

import com.arivanamin.healthcare.backend.patient.core.entity.Patient;
import com.arivanamin.healthcare.backend.patient.core.persistence.PatientPersistence;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReadPatientsQuery {
    
    private final PatientPersistence persistence;
    
    public List<Patient> execute () {
        return persistence.findAll();
    }
}
