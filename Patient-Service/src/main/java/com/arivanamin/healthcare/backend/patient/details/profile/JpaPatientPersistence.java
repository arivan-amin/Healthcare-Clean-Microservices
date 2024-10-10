package com.arivanamin.healthcare.backend.patient.details.profile;

import com.arivanamin.healthcare.backend.patient.core.entity.Patient;
import com.arivanamin.healthcare.backend.patient.core.persistence.PatientPersistence;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;

import java.util.*;

// @Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class JpaPatientPersistence implements PatientPersistence {
    
    private final PatientRepository repository;
    
    ModelMapper modelMapper = new ModelMapper();
    
    @Override
    public List<Patient> findAll () {
        return repository.findAll().stream().map(JpaPatient::toDomain).toList();
    }
    
    @Override
    public Optional<Patient> findById (UUID id) {
        return repository.findById(id).map(JpaPatient::toDomain);
    }
    
    @Override
    public UUID create (Patient patient) {
        return repository.save(JpaPatient.fromDomain(patient)).getId();
    }
    
    @Override
    public void update (UUID id, Patient patientEntity) {
        JpaPatient jpaPatient = repository.findById(id).orElseThrow();
        modelMapper.map(patientEntity, jpaPatient);
        repository.save(jpaPatient);
    }
    
    @Override
    public void delete (UUID id) {
        repository.deleteById(id);
    }
}
