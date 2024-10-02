package com.arivanamin.healthcare.backend.patient.details.mapper;

import com.arivanamin.healthcare.backend.patient.core.entity.Patient;
import com.arivanamin.healthcare.backend.patient.details.entity.JpaPatient;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;

import java.util.List;

@RequiredArgsConstructor
public class JpaPatientMapper {
    
    private ModelMapper modelMapper;
    
    public List<Patient> mapToEntity (List<JpaPatient> jpaEntities) {
        return jpaEntities.stream().map(this::mapJpaToEntity).toList();
    }
    
    public Patient mapJpaToEntity (JpaPatient jpaEntity) {
        return modelMapper.map(jpaEntity, Patient.class);
    }
    
    public List<JpaPatient> mapToJpa (List<Patient> entities) {
        return entities.stream().map(this::mapEntityToJpa).toList();
    }
    
    public JpaPatient mapEntityToJpa (Patient entity) {
        return modelMapper.map(entity, JpaPatient.class);
    }
}
