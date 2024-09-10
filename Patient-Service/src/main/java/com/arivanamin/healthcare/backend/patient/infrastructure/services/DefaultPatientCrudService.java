package com.arivanamin.healthcare.backend.patient.infrastructure.services;

import com.arivanamin.healthcare.backend.patient.domain.entities.Patient;
import com.arivanamin.healthcare.backend.patient.domain.services.PatientCrudService;
import org.jeasy.random.EasyRandom;

import java.util.List;
import java.util.UUID;

public class DefaultPatientCrudService implements PatientCrudService {
    
    EasyRandom random = new EasyRandom();
    
    @Override
    public List<Patient> getAllPatients () {
        return List.of(random.nextObject(Patient.class));
    }
    
    @Override
    public Patient getPatientById () {
        return random.nextObject(Patient.class);
    }
    
    @Override
    public Patient createPatient (Patient patient) {
        return random.nextObject(Patient.class);
    }
    
    @Override
    public Patient updatePatient (Patient patient) {
        return null;
    }
    
    @Override
    public void deletePatient (UUID id) {
        
    }
}
