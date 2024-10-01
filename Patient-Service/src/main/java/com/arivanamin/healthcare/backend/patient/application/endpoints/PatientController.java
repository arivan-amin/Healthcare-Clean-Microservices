package com.arivanamin.healthcare.backend.patient.application.endpoints;

import com.arivanamin.healthcare.backend.patient.application.response.*;
import com.arivanamin.healthcare.backend.patient.domain.command.*;
import com.arivanamin.healthcare.backend.patient.domain.entity.Patient;
import com.arivanamin.healthcare.backend.patient.domain.query.ReadPatientByIdQuery;
import com.arivanamin.healthcare.backend.patient.domain.query.ReadPatientsQuery;
import com.arivanamin.healthcare.backend.patient.application.mapper.PatientMapper;
import com.arivanamin.healthcare.backend.patient.infrastructure.response.*;
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
    
    private final ReadPatientsQuery readQuery;
    private final ReadPatientByIdQuery readByIdQuery;
    private final CreatePatientCommand createCommand;
    private final UpdatePatientCommand updateCommand;
    private final DeletePatientCommand deleteCommand;
    
    private final PatientMapper patientMapper = new PatientMapper();
    
    @GetMapping ("/v1/profiles")
    @Operation (summary = "Get a list of all patient profiles")
    @ResponseStatus (HttpStatus.OK)
    public ReadPatientsResponse getAllPatients () {
        List<Patient> patients = readQuery.execute();
        List<PatientResponse> responses =
            patients.stream().map(patientMapper::mapToResponse).toList();
        return new ReadPatientsResponse(responses);
    }
    
    @GetMapping ("/v1/profiles/{id}")
    @Operation (summary = "Get a single patient profile by id")
    @ResponseStatus (HttpStatus.OK)
    public ReadPatientByIdResponse getPatientById (@PathVariable UUID id) {
        Patient createdPatient = readByIdQuery.execute(id);
        return new ReadPatientByIdResponse(patientMapper.mapToResponse(createdPatient));
    }
    
    @PostMapping ("/v1/profiles")
    @Operation (summary = "Creates a patient profile")
    @ResponseStatus (HttpStatus.CREATED)
    public CreatePatientResponse createPatient (@RequestBody Patient patient) {
        return new CreatePatientResponse(createCommand.execute(patient));
    }
    
    @PutMapping ("/v1/profiles/{id}")
    @Operation (summary = "Updates a patient profile")
    @ResponseStatus (HttpStatus.CREATED)
    public void updatePatient (@PathVariable UUID id, @RequestBody Patient patient) {
        updateCommand.execute(id, patient);
    }
    
    @DeleteMapping ("/v1/profiles/{id}")
    @Operation (summary = "Deletes a patient profile")
    @ResponseStatus (HttpStatus.NO_CONTENT)
    public void deletePatient (@PathVariable UUID id) {
        deleteCommand.execute(id);
    }
}
