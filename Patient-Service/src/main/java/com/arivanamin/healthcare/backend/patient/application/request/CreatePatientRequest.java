package com.arivanamin.healthcare.backend.patient.application.request;

import com.arivanamin.healthcare.backend.core.domain.gender.Gender;
import lombok.*;

import java.time.LocalDate;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreatePatientRequest {
    
    UUID id;
    String firstName;
    String lastName;
    String email;
    LocalDate dateOfBirth;
    Gender gender;
    String address;
}
