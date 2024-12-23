package com.arivanamin.healthcare.backend.patient.application.request;

import com.arivanamin.healthcare.backend.base.domain.gender.Gender;
import com.arivanamin.healthcare.backend.patient.core.entity.Patient;
import lombok.*;
import org.modelmapper.ModelMapper;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreatePatientRequest {
    
    String firstName;
    String lastName;
    String email;
    LocalDate dateOfBirth;
    Gender gender;
    String address;
    
    public Patient toEntity () {
        return new ModelMapper().map(this, Patient.class);
    }
}
