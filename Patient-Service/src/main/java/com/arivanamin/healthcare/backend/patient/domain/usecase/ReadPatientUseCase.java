package com.arivanamin.healthcare.backend.patient.domain.usecase;

import com.arivanamin.healthcare.backend.patient.domain.entity.Patient;

import java.util.*;

public interface ReadPatientUseCase {
    
    public List<Patient> executeFindAll ();
    
    public Optional<Patient> executeFindById (UUID id);
}
