package com.arivanamin.healthcare.backend.patient.storage;

import com.arivanamin.healthcare.backend.patient.core.entity.Patient;
import com.arivanamin.healthcare.backend.patient.core.persistence.PatientStorage;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.*;

@RequiredArgsConstructor
@Slf4j
public class JpaPatientStorage implements PatientStorage {
    
    private final PatientRepository repository;
    
    @Override
    public List<Patient> findAll () {
        return repository.findAll()
            .stream()
            .map(JpaPatient::toDomain)
            .toList();
    }
    
    @Override
    public Optional<Patient> findById (UUID id) {
        return repository.findById(id)
            .map(JpaPatient::toDomain);
    }
    
    @Transactional
    @Override
    public UUID create (Patient patient) {
        return repository.save(JpaPatient.fromDomain(patient))
            .getId();
    }
    
    @Transactional
    @Override
    public void update (Patient patient) {
        repository.save(JpaPatient.fromDomain(patient));
    }
    
    @Transactional
    @Override
    public void delete (UUID id) {
        repository.deleteById(id);
    }
}
