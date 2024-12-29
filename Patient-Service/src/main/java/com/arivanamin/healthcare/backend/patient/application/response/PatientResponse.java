package com.arivanamin.healthcare.backend.patient.application.response;

import com.arivanamin.healthcare.backend.base.domain.gender.Gender;
import com.arivanamin.healthcare.backend.patient.core.entity.Patient;
import lombok.Value;
import org.modelmapper.ModelMapper;

import java.time.LocalDate;
import java.util.UUID;

@Value
public class PatientResponse {
    
    UUID id;
    String firstName;
    String lastName;
    String email;
    LocalDate dateOfBirth;
    Gender gender;
    String address;
    
    public static PatientResponse of (Patient patient) {
        return new ModelMapper().map(patient, PatientResponse.class);
    }
}
