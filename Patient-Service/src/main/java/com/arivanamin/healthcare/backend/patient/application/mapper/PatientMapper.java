package com.arivanamin.healthcare.backend.patient.application.mapper;

import com.arivanamin.healthcare.backend.patient.application.response.PatientResponse;
import com.arivanamin.healthcare.backend.patient.core.entity.Patient;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;

@RequiredArgsConstructor
public class PatientMapper {
    
    private final ModelMapper modelMapper;
    
    public PatientResponse mapToResponse (Patient entity) {
        return modelMapper.map(entity, PatientResponse.class);
    }
}
