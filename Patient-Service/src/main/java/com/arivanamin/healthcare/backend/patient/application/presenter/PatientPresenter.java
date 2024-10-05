package com.arivanamin.healthcare.backend.patient.application.presenter;

import com.arivanamin.healthcare.backend.patient.application.response.PatientResponse;
import com.arivanamin.healthcare.backend.patient.core.entity.Patient;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;

@RequiredArgsConstructor
public class PatientPresenter {
    
    private final ModelMapper modelMapper;
    
    public PatientResponse createReadPatientResponse (Patient entity) {
        return modelMapper.map(entity, PatientResponse.class);
    }
    
    public PatientResponse mapToEntityResponse (Patient entity) {
        return modelMapper.map(entity, PatientResponse.class);
    }
}
