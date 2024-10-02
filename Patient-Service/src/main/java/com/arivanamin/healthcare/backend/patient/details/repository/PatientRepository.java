package com.arivanamin.healthcare.backend.patient.details.repository;

import com.arivanamin.healthcare.backend.patient.details.entity.JpaPatient;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PatientRepository extends JpaRepository<JpaPatient, UUID> {
    
}
