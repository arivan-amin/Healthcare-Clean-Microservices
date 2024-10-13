package com.arivanamin.healthcare.backend.patient.core.command;

import com.arivanamin.healthcare.backend.patient.application.request.CreatePatientRequest;
import com.arivanamin.healthcare.backend.patient.core.entity.Patient;
import com.arivanamin.healthcare.backend.patient.core.persistence.PatientPersistence;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UpdatePatientCommand {
    
    private final PatientPersistence persistence;
    
    ModelMapper mapper = new ModelMapper();
    
    public void execute (UUID id, CreatePatientRequest request) {
        Patient patient = mapRequestToPatientEntity(request);
        persistence.update(id, patient);
    }
    
    private Patient mapRequestToPatientEntity (CreatePatientRequest request) {
        return mapper.map(request, Patient.class);
    }
}
