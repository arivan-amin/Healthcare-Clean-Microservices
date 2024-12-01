package com.arivanamin.healthcare.backend.patient.application.response;

import com.arivanamin.healthcare.backend.patient.core.entity.Patient;
import lombok.Value;

import java.util.List;

@Value
public class ReadPatientsResponse {
    
    List<PatientResponse> responses;
    
    public static ReadPatientsResponse of (List<Patient> patients) {
        return new ReadPatientsResponse(patients.stream()
            .map(PatientResponse::of)
            .toList());
    }
}
