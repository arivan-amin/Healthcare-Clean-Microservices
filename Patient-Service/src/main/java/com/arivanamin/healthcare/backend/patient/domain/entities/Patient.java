package com.arivanamin.healthcare.backend.patient.domain.entities;

import java.util.UUID;

public class Patient {
    
    UUID id;
    PersonalInfo personalInfo;
    ContactDetails contactDetails;
    Address address;
}
