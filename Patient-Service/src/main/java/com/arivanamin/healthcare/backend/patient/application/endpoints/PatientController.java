package com.arivanamin.healthcare.backend.patient.application.endpoints;

import com.arivanamin.healthcare.backend.patient.application.request.CreatePatientRequest;
import com.arivanamin.healthcare.backend.patient.application.request.UpdatePatientRequest;
import com.arivanamin.healthcare.backend.patient.application.response.*;
import com.arivanamin.healthcare.backend.patient.core.command.*;
import com.arivanamin.healthcare.backend.patient.core.query.ReadPatientByIdQuery;
import com.arivanamin.healthcare.backend.patient.core.query.ReadPatientsQuery;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

import static com.arivanamin.healthcare.backend.patient.application.config.PatientApiConfig.PROTECTED_API_BASE_PATH;

@Tag (name = "Patient Controller")
@RestController
@RequiredArgsConstructor
@Slf4j
class PatientController {
    
    private final ReadPatientsQuery readQuery;
    private final ReadPatientByIdQuery readByIdQuery;
    private final CreatePatientCommand createCommand;
    private final UpdatePatientCommand updateCommand;
    private final DeletePatientCommand deleteCommand;
    
    @GetMapping (PROTECTED_API_BASE_PATH + "/v1/accounts")
    @Cacheable (cacheNames = "patientsCache")
    @Operation (summary = "Get a list of patients")
    @ResponseStatus (HttpStatus.OK)
    public ReadPatientsResponse getAllPatients () {
        return ReadPatientsResponse.of(readQuery.execute());
    }
    
    @GetMapping (PROTECTED_API_BASE_PATH + "/v1/accounts/{id}")
    @Cacheable (cacheNames = "patientByIdCache")
    @Operation (summary = "Get a single patient by id")
    @ResponseStatus (HttpStatus.OK)
    public PatientResponse getPatientById (@PathVariable UUID id) {
        return PatientResponse.of(readByIdQuery.execute(id));
    }
    
    @PostMapping (PROTECTED_API_BASE_PATH + "/v1/accounts")
    @Operation (summary = "Creates a patient")
    @ResponseStatus (HttpStatus.CREATED)
    public CreatePatientResponse createPatient (@RequestBody @Valid CreatePatientRequest request) {
        UUID createdPatientId = createCommand.execute(request.toEntity());
        return CreatePatientResponse.of(createdPatientId);
    }
    
    @PutMapping (PROTECTED_API_BASE_PATH + "/v1/accounts/{id}")
    @Operation (summary = "Updates a patient")
    @ResponseStatus (HttpStatus.OK)
    public void updatePatient (@PathVariable UUID id,
                               @RequestBody @Valid UpdatePatientRequest request) {
        updateCommand.execute(request.toEntity(id));
    }
    
    @DeleteMapping (PROTECTED_API_BASE_PATH + "/v1/accounts/{id}")
    @Operation (summary = "Deletes a patient")
    @ResponseStatus (HttpStatus.NO_CONTENT)
    public void deletePatient (@PathVariable UUID id) {
        deleteCommand.execute(id);
    }
}
