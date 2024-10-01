package com.arivanamin.healthcare.backend.patient.application.response;

import lombok.Value;

import java.util.List;

@Value
public class ReadPatientsResponse {
    
    List<PatientResponse> patients;
}
