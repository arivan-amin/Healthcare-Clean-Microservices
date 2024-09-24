package com.arivanamin.healthcare.backend.patient.domain.query;

import com.arivanamin.healthcare.backend.patient.domain.entity.Patient;
import lombok.Value;

import java.util.List;

@Value
public class ReadPatientsResponse {
    
    List<Patient> patients;
}
