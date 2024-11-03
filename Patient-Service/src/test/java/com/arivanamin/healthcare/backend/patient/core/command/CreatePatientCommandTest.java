package com.arivanamin.healthcare.backend.patient.core.command;

import com.arivanamin.healthcare.backend.core.domain.testing.BaseUnitTest;
import com.arivanamin.healthcare.backend.patient.application.request.CreatePatientRequest;
import com.arivanamin.healthcare.backend.patient.core.entity.Patient;
import com.arivanamin.healthcare.backend.patient.core.exception.PatientAlreadyExistsException;
import com.arivanamin.healthcare.backend.patient.core.persistence.PatientPersistence;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatException;
import static org.mockito.Mockito.*;

class CreatePatientCommandTest implements BaseUnitTest {
    
    private final String emailAddress = "echo@mail.com";
    private final UUID createdPatientId = UUID.randomUUID();
    
    private PatientPersistence persistence;
    private CreatePatientCommand command;
    
    private CreatePatientRequest request;
    
    @Test
    void shouldThrowExceptionWhenPatientExists () {
        givenCommandWithMockFindAll();
        whenEmailIsDuplicate();
        thenThrowPatientAlreadyExistsException();
    }
    
    private void givenCommandWithMockFindAll () {
        persistence = mock(PatientPersistence.class);
        command = new CreatePatientCommand(persistence);
        Patient patient = RANDOM.nextObject(Patient.class);
        patient.setEmail(emailAddress);
        when(persistence.findAll()).thenReturn(List.of(patient));
    }
    
    private void whenEmailIsDuplicate () {
        request = RANDOM.nextObject(CreatePatientRequest.class);
        request.setEmail(emailAddress);
    }
    
    private void thenThrowPatientAlreadyExistsException () {
        assertThatException().isThrownBy(() -> command.execute(request))
            .isInstanceOf(PatientAlreadyExistsException.class);
    }
    
    @Test
    void shouldCallPersistenceCreate () {
        givenCommandWithMockPersistence();
        whenCommandIsExecuted();
        thenVerifyCommandCallsCreate();
    }
    
    private void givenCommandWithMockPersistence () {
        persistence = mock(PatientPersistence.class);
        command = new CreatePatientCommand(persistence);
        when(persistence.create(any())).thenReturn(createdPatientId);
        request = RANDOM.nextObject(CreatePatientRequest.class);
    }
    
    private UUID whenCommandIsExecuted () {
        return command.execute(request);
    }
    
    private void thenVerifyCommandCallsCreate () {
        verify(persistence, times(1)).create(request.toEntity());
    }
    
    @Test
    void shouldReturnResultOfPersistenceCreate () {
        givenCommandWithMockPersistence();
        UUID resultId = whenCommandIsExecuted();
        thenVerifyCreateResultIsReturned(resultId);
    }
    
    private void thenVerifyCreateResultIsReturned (UUID resultId) {
        assertThat(resultId).isSameAs(createdPatientId);
    }
}
