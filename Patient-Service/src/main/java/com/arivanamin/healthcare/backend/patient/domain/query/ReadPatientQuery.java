package com.arivanamin.healthcare.backend.patient.domain.query;

import com.arivanamin.healthcare.backend.patient.domain.entity.Patient;

import java.util.List;

public interface ReadPatientQuery {
    
    public List<Patient> execute ();
}
