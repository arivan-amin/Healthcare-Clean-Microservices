package com.arivanamin.healthcare.backend.patient.application.endpoints;

import com.arivanamin.healthcare.backend.base.domain.audit.AuditEvent;
import com.arivanamin.healthcare.backend.base.domain.audit.AuditEventPublisher;
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

import static com.arivanamin.healthcare.backend.patient.application.config.PatientApiURLs.*;

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
    
    private final AuditEventPublisher auditPublisher;
    private AuditEvent event;
    
    @GetMapping (GET_PATIENTS_URL)
    @Cacheable (cacheNames = "patientsCache")
    @Operation (summary = "Get a list of patients")
    @ResponseStatus (HttpStatus.OK)
    public ReadPatientsResponse getAllPatients () {
        return ReadPatientsResponse.of(readQuery.execute());
    }
    
    @GetMapping (GET_PATIENT_BY_ID_URL)
    @Cacheable (cacheNames = "patientByIdCache")
    @Operation (summary = "Get a single patient by id")
    @ResponseStatus (HttpStatus.OK)
    public PatientResponse getPatientById (@PathVariable UUID id) {
        
        // todo 12/21/24 - shouldn't be done here, aspect is a better place
        // event.setLocation(PROTECTED_API + "/v1/accounts/{id})");
        // event.setData(String.valueOf(id));
        // event.setAction("get patient by id");
        
        // auditPublisher.sendAuditLog(AuditTopics.API_AUDIT_TOPIC, event);
        return PatientResponse.of(readByIdQuery.execute(id));
    }
    
    @PostMapping (CREATE_PATIENT_URL)
    @Operation (summary = "Creates a patient")
    @ResponseStatus (HttpStatus.CREATED)
    public CreatePatientResponse createPatient (@RequestBody @Valid CreatePatientRequest request) {
        UUID createdPatientId = createCommand.execute(request.toEntity());
        return CreatePatientResponse.of(createdPatientId);
    }
    
    @PutMapping (UPDATE_PATIENT_URL)
    @Operation (summary = "Updates a patient")
    @ResponseStatus (HttpStatus.OK)
    public void updatePatient (@PathVariable UUID id,
                               @RequestBody @Valid UpdatePatientRequest request) {
        updateCommand.execute(request.toEntity(id));
    }
    
    @DeleteMapping (DELETE_PATIENT_URL)
    @Operation (summary = "Deletes a patient")
    @ResponseStatus (HttpStatus.NO_CONTENT)
    public void deletePatient (@PathVariable UUID id) {
        deleteCommand.execute(id);
    }
}
