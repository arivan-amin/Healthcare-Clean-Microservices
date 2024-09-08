package com.arivanamin.healthcare.backend.patient.domain.entities;

import com.arivanamin.healthcare.core.domain.gender.Gender;

import java.time.LocalDate;
import java.util.UUID;

public class PersonalInfo {
    
    UUID id;
    String firstName;
    String middleName;
    String lastName;
    LocalDate dateOfBirth;
    Gender gender;
}
