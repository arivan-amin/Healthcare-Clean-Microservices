package com.arivanamin.healthcare.backend.patient.core.exception;

public class PatientNotFoundException extends RuntimeException {
    
    public PatientNotFoundException (String message) {
        super(message);
    }
}
