package com.arivanamin.healthcare.backend.patient.infrastructure.repository;

import com.arivanamin.healthcare.backend.patient.domain.entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface PatientRepository extends JpaRepository<Patient, UUID> {
    
    Optional<Patient> findById (UUID id);
}
