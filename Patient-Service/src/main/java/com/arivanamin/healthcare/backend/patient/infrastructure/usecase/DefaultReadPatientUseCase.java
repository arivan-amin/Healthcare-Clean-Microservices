package com.arivanamin.healthcare.backend.patient.infrastructure.usecase;

import com.arivanamin.healthcare.backend.patient.domain.entity.Patient;
import com.arivanamin.healthcare.backend.patient.domain.persistence.PatientPersistence;
import com.arivanamin.healthcare.backend.patient.domain.usecase.ReadPatientUseCase;
import lombok.RequiredArgsConstructor;
import org.jeasy.random.EasyRandom;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DefaultReadPatientUseCase implements ReadPatientUseCase {
    
    private final PatientPersistence persistence;
    
    EasyRandom random = new EasyRandom();
    
    @Override
    public List<Patient> executeFindAll () {
        return List.of(random.nextObject(Patient.class), random.nextObject(Patient.class),
            random.nextObject(Patient.class));
    }
    
    @Override
    public Patient executeFindById () {
        return random.nextObject(Patient.class);
    }
}
