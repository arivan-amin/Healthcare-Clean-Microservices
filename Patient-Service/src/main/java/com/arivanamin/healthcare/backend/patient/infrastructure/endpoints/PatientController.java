package com.arivanamin.healthcare.backend.patient.infrastructure.endpoints;

import com.arivanamin.healthcare.backend.patient.domain.entity.Patient;
import com.arivanamin.healthcare.backend.patient.domain.usecase.*;
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
    
    private ReadPatientUseCase readUseCase;
    private CreatePatientUseCase createUseCase;
    private UpdatePatientUseCase updateUseCase;
    private DeletePatientUseCase deleteUseCase;
    
    @GetMapping ("/v1/profiles")
    @Operation (summary = "Get a list of all patient profiles")
    @ResponseStatus (HttpStatus.OK)
    public List<Patient> getAllPatients () {
        return readUseCase.executeFindAll();
    }
    
    @GetMapping ("/v1/profiles/{id}")
    @Operation (summary = "Get a single patient profile by id")
    @ResponseStatus (HttpStatus.OK)
    public Patient getPatientById (@PathVariable UUID id) {
        log.info("received patientId = {}", id);
        return readUseCase.executeFindById();
    }
    
    @PostMapping ("/v1/profiles")
    @Operation (summary = "Creates a patient profile")
    @ResponseStatus (HttpStatus.CREATED)
    public Patient createPatient (@PathVariable Patient patient) {
        log.info("received patient to create = {}", patient);
        return createUseCase.execute(patient);
    }
    
    @PostMapping ("/v1/profiles")
    @Operation (summary = "Updates a patient profile")
    @ResponseStatus (HttpStatus.CREATED)
    public Patient updatePatient (@PathVariable Patient patient) {
        log.info("received patient to update = {}", patient);
        return updateUseCase.execute(patient);
    }
    
    @DeleteMapping ("/v1/profiles/{id}")
    @Operation (summary = "Deletes a patient profile")
    @ResponseStatus (HttpStatus.NO_CONTENT)
    public void deletePatient (@PathVariable UUID id) {
        log.info("received patient to delete = {}", id);
        deleteUseCase.execute(id);
    }
}
