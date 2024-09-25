package com.arivanamin.healthcare.backend.patient.domain.query;

import com.arivanamin.healthcare.backend.patient.domain.query.response.PatientResponse;
import lombok.Value;

import java.util.List;

@Value
public class ReadPatientsResponse {
    
    List<PatientResponse> patients;
}
