package com.arivanamin.healthcare.backend.patient.infrastructure.persistence;

import com.arivanamin.healthcare.backend.patient.domain.entity.Patient;
import com.arivanamin.healthcare.backend.patient.domain.persistence.PatientPersistence;
import com.arivanamin.healthcare.backend.patient.infrastructure.repository.JpaPatientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class SpringPatientPersistence implements PatientPersistence {
    
    private final JpaPatientRepository repository;
    
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
    public Patient update (Patient patient) {
        return repository.save(patient);
    }
    
    @Override
    public void delete (UUID id) {
        repository.deleteById(id);   
    }
}
