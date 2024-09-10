package com.arivanamin.healthcare.backend.patient.infrastructure.endpoints;

import com.arivanamin.healthcare.backend.patient.domain.entities.Patient;
import com.arivanamin.healthcare.backend.patient.domain.services.PatientCrudService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping ("/api/patients")
@RequiredArgsConstructor
@Slf4j
public class PatientController {
    
    private PatientCrudService service;
    
    @GetMapping ("/v1/profiles")
    @Operation (summary = "Get list of all patient profiles")
    @ResponseStatus (HttpStatus.OK)
    public List<Patient> getAllPatients () {
        return service.getAllPatients();
    }
    
    @GetMapping ("/v1/profiles/{patientId}")
    @Operation (summary = "Get single patient profile by id")
    @ResponseStatus (HttpStatus.OK)
    public Patient getPatientById (@PathVariable UUID patientId) {
        log.info("received patientId = {}", patientId);
        return service.getPatientById();
    }
    
    @PostMapping ("/v1/profiles")
    @Operation (summary = "Creates Patient profile")
    @ResponseStatus (HttpStatus.CREATED)
    public Patient createPatient (@PathVariable Patient patient) {
        log.info("received patient to create = {}", patient);
        return service.createPatient(patient);
    }
}
