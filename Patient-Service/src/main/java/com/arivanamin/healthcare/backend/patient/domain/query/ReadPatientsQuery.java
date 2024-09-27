package com.arivanamin.healthcare.backend.patient.domain.query;

import com.arivanamin.healthcare.backend.patient.domain.entity.Patient;
import com.arivanamin.healthcare.backend.patient.domain.persistence.PatientPersistence;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReadPatientsQuery {
    
    private final PatientPersistence persistence;
    
    public ReadPatientsResponse execute () {
        List<Patient> patients = persistence.getAllPatients();
        return new ReadPatientsResponse(null);
    }
}
