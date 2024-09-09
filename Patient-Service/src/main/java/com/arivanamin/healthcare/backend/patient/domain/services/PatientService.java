package com.arivanamin.healthcare.backend.patient.domain.services;

import com.arivanamin.healthcare.backend.patient.domain.entities.Patient;

import java.util.List;
import java.util.UUID;

public interface PatientService {
    
    public List<Patient> getAllPatients ();
    
    public Patient getPatientById ();
    
    public Patient createPatient (Patient patient);
    
    public Patient updatePatient (Patient patient);
    
    public void deletePatient (UUID id);
}
