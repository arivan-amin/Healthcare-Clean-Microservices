package com.arivanamin.healthcare.backend.patient.infrastructure.endpoints;

import com.arivanamin.healthcare.backend.patient.domain.command.*;
import com.arivanamin.healthcare.backend.patient.domain.entity.Patient;
import com.arivanamin.healthcare.backend.patient.domain.query.*;
import com.arivanamin.healthcare.backend.patient.domain.query.response.PatientResponse;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping ("/api/patients")
@RequiredArgsConstructor
@Slf4j
public class PatientController {
    
    private final ReadPatientsQuery readQuery;
    private final ReadPatientByIdQuery readByIdQuery;
    private final CreatePatientCommand createCommand;
    private final UpdatePatientCommand updateCommand;
    private final DeletePatientCommand deleteCommand;
    
    @GetMapping ("/v1/profiles")
    @Operation (summary = "Get a list of all patient profiles")
    @ResponseStatus (HttpStatus.OK)
    public ReadPatientsResponse getAllPatients () {
        return readQuery.execute();
    }
    
    @GetMapping ("/v1/profiles/{id}")
    @Operation (summary = "Get a single patient profile by id")
    @ResponseStatus (HttpStatus.OK)
    public PatientResponse getPatientById (@PathVariable UUID id) {
        return readByIdQuery.execute(id);
    }
    
    @PostMapping ("/v1/profiles")
    @Operation (summary = "Creates a patient profile")
    @ResponseStatus (HttpStatus.CREATED)
    public Patient createPatient (@RequestBody Patient patient) {
        return createCommand.execute(patient);
    }
    
    @PutMapping ("/v1/profiles/{id}")
    @Operation (summary = "Updates a patient profile")
    @ResponseStatus (HttpStatus.CREATED)
    public Patient updatePatient (@PathVariable UUID id, @RequestBody Patient patient) {
        return updateCommand.execute(id, patient);
    }
    
    @DeleteMapping ("/v1/profiles/{id}")
    @Operation (summary = "Deletes a patient profile")
    @ResponseStatus (HttpStatus.NO_CONTENT)
    public void deletePatient (@PathVariable UUID id) {
        deleteCommand.execute(id);
    }
}
