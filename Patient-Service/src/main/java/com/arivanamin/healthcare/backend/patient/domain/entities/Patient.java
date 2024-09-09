package com.arivanamin.healthcare.backend.patient.domain.entities;

import com.arivanamin.healthcare.core.domain.gender.Gender;
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
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    UUID id;
    
    String firstName;
    String lastName;
    String email;
    LocalDate dateOfBirth;
    Gender gender;
    String address;
}
