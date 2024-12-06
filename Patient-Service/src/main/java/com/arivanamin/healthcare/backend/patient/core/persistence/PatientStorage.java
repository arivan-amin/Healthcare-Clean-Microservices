package com.arivanamin.healthcare.backend.patient.core.persistence;

import com.arivanamin.healthcare.backend.patient.core.entity.Patient;

import java.util.*;

public interface PatientStorage {
    
    List<Patient> findAll ();
    
    Optional<Patient> findById (UUID id);
    
    UUID create (Patient patient);
    
    void update (Patient patient);
    
    void delete (UUID id);
}
