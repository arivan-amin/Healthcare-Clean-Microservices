package com.arivanamin.healthcare.backend.patient.domain.entity;

import com.arivanamin.healthcare.backend.core.domain.gender.Gender;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class Patient {
    
    @Id
    @GeneratedValue (strategy = GenerationType.AUTO)
    UUID id;
    
    String firstName;
    String lastName;
    String email;
    LocalDate dateOfBirth;
    Gender gender;
    String address;
}