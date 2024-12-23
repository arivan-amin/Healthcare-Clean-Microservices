package com.arivanamin.healthcare.backend.patient.application.request;

import com.arivanamin.healthcare.backend.base.domain.gender.Gender;
import com.arivanamin.healthcare.backend.patient.core.entity.Patient;
import lombok.*;
import org.modelmapper.ModelMapper;

import java.time.LocalDate;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdatePatientRequest {
    
    String firstName;
    String lastName;
    String email;
    LocalDate dateOfBirth;
    Gender gender;
    String address;
    
    public Patient toEntity (UUID id) {
        Patient patient = new ModelMapper().map(this, Patient.class);
        patient.setId(id);
        return patient;
    }
}
