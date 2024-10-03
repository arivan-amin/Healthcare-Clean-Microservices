package com.arivanamin.healthcare.backend.patient.details.mapper;

import com.arivanamin.healthcare.backend.patient.core.entity.Patient;
import com.arivanamin.healthcare.backend.patient.details.entity.JpaPatient;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;

import java.util.List;

@RequiredArgsConstructor
public class JpaPatientMapper {
    
    private ModelMapper modelMapper;
    
    public List<Patient> mapToEntities (List<JpaPatient> jpaEntities) {
        return jpaEntities.stream().map(this::mapToEntity).toList();
    }
    
    public Patient mapToEntity (JpaPatient jpaEntity) {
        return modelMapper.map(jpaEntity, Patient.class);
    }
    
    public JpaPatient mapToJpa (Patient entity) {
        return modelMapper.map(entity, JpaPatient.class);
    }
}
