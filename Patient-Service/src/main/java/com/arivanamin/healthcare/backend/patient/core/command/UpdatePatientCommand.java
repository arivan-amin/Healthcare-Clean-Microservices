package com.arivanamin.healthcare.backend.patient.core.command;

import com.arivanamin.healthcare.backend.patient.application.request.CreatePatientRequest;
import com.arivanamin.healthcare.backend.patient.core.persistence.PatientPersistence;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UpdatePatientCommand {
    
    private final PatientPersistence persistence;
    
    private final ModelMapper mapper;
    
    public void execute (UUID id, CreatePatientRequest request) {
        
        persistence.update(id, null);
    }
}
