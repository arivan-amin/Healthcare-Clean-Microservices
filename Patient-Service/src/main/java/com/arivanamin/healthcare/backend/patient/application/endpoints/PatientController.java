package com.arivanamin.healthcare.backend.patient.application.endpoints;

import com.arivanamin.healthcare.backend.patient.application.presenter.PatientPresenter;
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
    
    private final PatientPresenter presenter;
    
    @GetMapping ("/v1/profiles")
    @Operation (summary = "Get a list of all patient profiles")
    @ResponseStatus (HttpStatus.OK)
    public ReadPatientsResponse getAllPatients () {
        List<Patient> patients = readQuery.execute();
        List<PatientResponse> responses =
            patients.stream().map(presenter::mapToEntityResponse).toList();
        return new ReadPatientsResponse(responses);
    }
    
    @GetMapping ("/v1/profiles/{id}")
    @Operation (summary = "Get a single patient profile by id")
    @ResponseStatus (HttpStatus.OK)
    public PatientResponse getPatientById (@PathVariable UUID id) {
        Patient patient = readByIdQuery.execute(id);
        return presenter.mapToEntityResponse(patient);
    }
    
    @PostMapping ("/v1/profiles")
    @Operation (summary = "Creates a patient profile")
    @ResponseStatus (HttpStatus.CREATED)
    public CreatePatientResponse createPatient (@RequestBody (required = true) Patient patient) {
        UUID createdPatientId = createCommand.execute(patient);
        return new CreatePatientResponse(createdPatientId);
    }
    
    @PutMapping ("/v1/profiles/{id}")
    @Operation (summary = "Updates a patient profile")
    @ResponseStatus (HttpStatus.OK)
    public void updatePatient (@PathVariable (required = true) UUID id,
                               @RequestBody (required = true) CreatePatientRequest request) {
        updateCommand.execute(id, request);
    }
    
    @DeleteMapping ("/v1/profiles/{id}")
    @Operation (summary = "Deletes a patient profile")
    @ResponseStatus (HttpStatus.NO_CONTENT)
    public void deletePatient (@PathVariable (required = true) UUID id) {
        deleteCommand.execute(id);
    }
}
