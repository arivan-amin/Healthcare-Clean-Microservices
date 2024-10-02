package com.arivanamin.healthcare.backend.patient.core.persistence;

import com.arivanamin.healthcare.backend.patient.core.entity.Patient;

import java.util.*;

public interface PatientPersistence {
    
    List<Patient> getAllPatients ();
    
    Optional<Patient> getPatientById (UUID id);
    
    UUID create (Patient patient);
    
    void update (UUID id, Patient patient);
    
    void delete (UUID id);
}
