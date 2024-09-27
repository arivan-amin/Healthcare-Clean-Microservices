package com.arivanamin.healthcare.backend.patient.infrastructure.mapper;

import com.arivanamin.healthcare.backend.patient.domain.entity.Patient;
import com.arivanamin.healthcare.backend.patient.infrastructure.response.PatientResponse;

public class PatientMapper {
    
    public PatientResponse mapToResponse (Patient entity) {
        return new PatientResponse(entity.getId(), entity.getFirstName(), entity.getLastName(),
            entity.getEmail(), entity.getDateOfBirth(), entity.getGender(), entity.getAddress());
    }
}
