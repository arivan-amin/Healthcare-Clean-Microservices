package com.arivanamin.healthcare.backend.patient.infrastructure.response;

import lombok.Value;

import java.util.List;

@Value
public class ReadPatientsResponse {
    
    List<PatientResponse> patients;
}
