package com.arivanamin.healthcare.backend.patient.details.persistence;

import com.arivanamin.healthcare.backend.patient.core.entity.Patient;
import com.arivanamin.healthcare.backend.patient.core.persistence.PatientPersistence;
import com.arivanamin.healthcare.backend.patient.details.entity.JpaPatient;
import com.arivanamin.healthcare.backend.patient.details.mapper.JpaPatientMapper;
import com.arivanamin.healthcare.backend.patient.details.repository.PatientRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
@Slf4j
public class JpaPatientPersistence implements PatientPersistence {
    
    private final PatientRepository repository;
    
    private final JpaPatientMapper mapper;
    
    @Override
    public List<Patient> getAllPatients () {
        return mapper.mapToEntity(repository.findAll());
    }
    
    @Override
    public Optional<Patient> getPatientById (UUID id) {
        JpaPatient jpaEntity = repository.findById(id).get();
        return Optional.ofNullable(mapper.mapJpaToEntity(jpaEntity));
    }
    
    @Override
    public UUID create (Patient patient) {
        return repository.save(mapper.mapEntityToJpa(patient)).getId();
    }
    
    @Override
    public void update (UUID id, Patient patient) {
        JpaPatient patientById = repository.findById(id).get();
        repository.save(patientById);
    }
    
    @Override
    public void delete (UUID id) {
        repository.deleteById(id);
    }
}
