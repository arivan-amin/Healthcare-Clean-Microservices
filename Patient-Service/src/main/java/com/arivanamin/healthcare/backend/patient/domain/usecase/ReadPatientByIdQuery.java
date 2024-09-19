package com.arivanamin.healthcare.backend.patient.domain.usecase;

import com.arivanamin.healthcare.backend.patient.domain.entity.Patient;

import java.util.*;

public interface ReadPatientByIdQuery {
    
    public Optional<Patient> execute (UUID id);
}
