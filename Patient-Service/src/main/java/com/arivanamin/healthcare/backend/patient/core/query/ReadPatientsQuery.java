package com.arivanamin.healthcare.backend.patient.core.query;

import com.arivanamin.healthcare.backend.patient.core.entity.Patient;
import com.arivanamin.healthcare.backend.patient.core.persistence.PatientStorage;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class ReadPatientsQuery {
    
    private final PatientStorage storage;
    
    public List<Patient> execute () {
        return storage.findAll();
    }
}
