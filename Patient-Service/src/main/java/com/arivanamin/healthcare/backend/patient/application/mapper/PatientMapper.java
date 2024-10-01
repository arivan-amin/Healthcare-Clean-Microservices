package com.arivanamin.healthcare.backend.patient.application.mapper;

import com.arivanamin.healthcare.backend.patient.domain.entity.Patient;
import com.arivanamin.healthcare.backend.patient.application.response.PatientResponse;

public class PatientMapper {
    
    public PatientResponse mapToResponse (Patient entity) {
        return new PatientResponse(entity.getId(), entity.getFirstName(), entity.getLastName(),
            entity.getEmail(), entity.getDateOfBirth(), entity.getGender(), entity.getAddress());
    }
}
