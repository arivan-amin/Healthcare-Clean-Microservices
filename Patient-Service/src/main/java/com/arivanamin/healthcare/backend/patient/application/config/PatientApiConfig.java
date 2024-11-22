package com.arivanamin.healthcare.backend.patient.application.config;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor (access = AccessLevel.PRIVATE)
public final class PatientApiConfig {
    
    public static final String PROTECTED_API_BASE_PATH = "/patients/protected";
    public static final String PUBLIC_API_BASE_PATH = "/patients/public";
}
