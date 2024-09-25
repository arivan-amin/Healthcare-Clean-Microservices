package com.arivanamin.healthcare.backend.patient.domain.entity;

import com.arivanamin.healthcare.backend.core.domain.gender.Gender;
import jakarta.persistence.*;
import lombok.*;

import jakarta.validation.constraints.*;
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
    
    @NotBlank
    String firstName;
    
    @NotBlank
    String lastName;
    
    @Email
    String email;
    
    @Past
    LocalDate dateOfBirth;
    
    @NotNull
    Gender gender;
    
    @NotBlank
    String address;
}
