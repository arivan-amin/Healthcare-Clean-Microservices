package com.arivanamin.healthcare.backend.patient.domain.usecase;

import com.arivanamin.healthcare.backend.patient.domain.entity.Patient;

import java.util.List;

public interface ReadPatientQuery {
    
    public List<Patient> execute ();
}
