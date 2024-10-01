package com.arivanamin.healthcare.backend.patient.application.response;

import com.arivanamin.healthcare.backend.core.domain.gender.Gender;
import lombok.Value;

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
}
