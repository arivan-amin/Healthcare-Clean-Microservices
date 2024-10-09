package com.arivanamin.healthcare.backend.patient.application.response;

import com.arivanamin.healthcare.backend.core.domain.gender.Gender;
import com.arivanamin.healthcare.backend.patient.core.entity.Patient;
import lombok.*;
import org.modelmapper.ModelMapper;

import java.time.LocalDate;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PatientResponse {
    
    UUID id;
    String firstName;
    String lastName;
    String email;
    LocalDate dateOfBirth;
    Gender gender;
    String address;
    
    public static PatientResponse of (Patient patient) {
        ModelMapper mapper = new ModelMapper();
        return mapper.map(patient, PatientResponse.class);
    }
}
