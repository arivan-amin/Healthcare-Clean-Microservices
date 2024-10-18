package com.arivanamin.healthcare.backend.patient.application.endpoints;

import com.arivanamin.healthcare.backend.patient.application.request.CreatePatientRequest;
import com.arivanamin.healthcare.backend.patient.application.response.*;
import com.arivanamin.healthcare.backend.patient.core.command.*;
import com.arivanamin.healthcare.backend.patient.core.entity.Patient;
import com.arivanamin.healthcare.backend.patient.core.query.ReadPatientByIdQuery;
import com.arivanamin.healthcare.backend.patient.core.query.ReadPatientsQuery;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

import static com.arivanamin.healthcare.backend.patient.application.config.ApiConfig.API_BASE_PATH;

@RestController
@RequestMapping (API_BASE_PATH)
@RequiredArgsConstructor
@Slf4j
public class PatientController {
    
    private final ReadPatientsQuery readQuery;
    private final ReadPatientByIdQuery readByIdQuery;
    private final CreatePatientCommand createCommand;
    private final UpdatePatientCommand updateCommand;
    private final DeletePatientCommand deleteCommand;
    
    @GetMapping ("/v1/patients")
    @Operation (summary = "Get a list of patients")
    @ResponseStatus (HttpStatus.OK)
    public ReadPatientsResponse getAllPatients () {
        return ReadPatientsResponse.of(readQuery.execute());
    }
    
    @GetMapping ("/v1/patients/{id}")
    @Operation (summary = "Get a single patient by id")
    @ResponseStatus (HttpStatus.OK)
    public PatientResponse getPatientById (@PathVariable UUID id) {
        return PatientResponse.of(readByIdQuery.execute(id));
    }
    
    @PostMapping ("/v1/patients")
    @Operation (summary = "Creates a patient")
    @ResponseStatus (HttpStatus.CREATED)
    public CreatePatientResponse createPatient (@RequestBody Patient patient) {
        UUID createdPatientId = createCommand.execute(patient);
        return CreatePatientResponse.of(createdPatientId);
    }
    
    @PutMapping ("/v1/patients/{id}")
    @Operation (summary = "Updates a patient")
    @ResponseStatus (HttpStatus.OK)
    public void updatePatient (@PathVariable UUID id, @RequestBody CreatePatientRequest request) {
        updateCommand.execute(id, request);
    }
    
    @DeleteMapping ("/v1/patients/{id}")
    @Operation (summary = "Deletes a patient")
    @ResponseStatus (HttpStatus.NO_CONTENT)
    public void deletePatient (@PathVariable UUID id) {
        deleteCommand.execute(id);
    }
}
