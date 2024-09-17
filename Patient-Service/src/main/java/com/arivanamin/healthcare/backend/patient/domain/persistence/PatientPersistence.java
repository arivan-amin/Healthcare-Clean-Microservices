package com.arivanamin.healthcare.backend.patient.domain.persistence;

import com.arivanamin.healthcare.backend.patient.domain.entity.Patient;

import java.util.*;

public interface PatientPersistence {
    
    List<Patient> getAllPatients ();
    
    Optional<Patient> getPatientById (UUID id);
    
    Patient create (Patient patient);
    
    Patient update (UUID id, Patient patient);
    
    void delete (UUID id);
}
