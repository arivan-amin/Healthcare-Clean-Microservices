package com.arivanamin.healthcare.backend.patient.core.exception;

public class PatientNotFoundException extends Exception {
    
    public PatientNotFoundException (String message) {
        super(message);
    }
}
