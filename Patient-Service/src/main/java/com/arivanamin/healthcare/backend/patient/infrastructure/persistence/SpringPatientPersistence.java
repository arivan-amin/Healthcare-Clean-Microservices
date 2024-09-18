package com.arivanamin.healthcare.backend.patient.infrastructure.persistence;

import com.arivanamin.healthcare.backend.patient.domain.entity.Patient;
import com.arivanamin.healthcare.backend.patient.domain.persistence.PatientPersistence;
import com.arivanamin.healthcare.backend.patient.infrastructure.repository.PatientRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
@Slf4j
public class SpringPatientPersistence implements PatientPersistence {
    
    private final PatientRepository repository;
    
    @Override
    public List<Patient> getAllPatients () {
        return repository.findAll();
    }
    
    @Override
    public Optional<Patient> getPatientById (UUID id) {
        return repository.findById(id);
    }
    
    @Override
    public Patient create (Patient patient) {
        return repository.save(patient);
    }
    
    @Override
    public Patient update (UUID id, Patient patient) {
        Patient patientById = getPatientById(id).get();
        return repository.save(patient);
    }
    
    @Override
    public void delete (UUID id) {
        repository.deleteById(id);
    }
}
