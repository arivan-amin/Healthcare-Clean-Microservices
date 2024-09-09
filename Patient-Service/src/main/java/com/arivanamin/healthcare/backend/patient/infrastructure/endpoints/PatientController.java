package com.arivanamin.healthcare.backend.patient.infrastructure.endpoints;

import com.arivanamin.healthcare.backend.patient.domain.entities.Patient;
import com.arivanamin.healthcare.backend.patient.domain.services.PatientService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jeasy.random.EasyRandom;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
public class PatientController {
    
    private PatientService service;
    
    @GetMapping ("/api/patients/v1")
    @ResponseStatus (HttpStatus.CREATED)
    public List<Patient> getAllPatients () {
        EasyRandom random = new EasyRandom();
        return Collections.singletonList(random.nextObject(Patient.class));
    }
}
