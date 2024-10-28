package com.arivanamin.healthcare.backend.patient.core.query;

import com.arivanamin.healthcare.backend.core.domain.testing.BaseUnitTest;
import com.arivanamin.healthcare.backend.patient.core.entity.Patient;
import com.arivanamin.healthcare.backend.patient.core.exception.PatientNotFoundException;
import com.arivanamin.healthcare.backend.patient.core.persistence.PatientPersistence;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@Tag ("current")
class ReadPatientByIdQueryTest implements BaseUnitTest {
    
    private final Patient patient = RANDOM.nextObject(Patient.class);
    private final UUID id = UUID.randomUUID();
    
    private PatientPersistence persistence;
    private ReadPatientByIdQuery query;
    
    @Test
    void shouldCallPersistenceFindById () {
        givenQueryWithMockPersistence();
        whenQueryIsExecuted();
        thenVerifyQueryCallsFindById();
    }
    
    private void givenQueryWithMockPersistence () {
        persistence = mock(PatientPersistence.class);
        when(persistence.findById(id)).thenReturn(Optional.of(patient));
        query = new ReadPatientByIdQuery(persistence);
    }
    
    private Patient whenQueryIsExecuted () {
        return query.execute(id);
    }
    
    private void thenVerifyQueryCallsFindById () {
        verify(persistence, times(1)).findById(id);
    }
    
    @Test
    void shouldReturnResultFromPersistenceFindById () {
        givenQueryWithMockPersistence();
        Patient result = whenQueryIsExecuted();
        thenVerifyFindByIdResultIsReturned(result);
    }
    
    private void thenVerifyFindByIdResultIsReturned (Patient result) {
        assertThat(result).isSameAs(patient);
    }
    
    @Test
    void shouldThrowWhenPatientIsNotFound () {
        givenQueryWithMockThatThrowsWhenIdNotFound();
        thenAssertQueryThrowsPatientNotFoundException();
    }
    
    private void givenQueryWithMockThatThrowsWhenIdNotFound () {
        persistence = mock(PatientPersistence.class);
        when(persistence.findById(id)).thenThrow(PatientNotFoundException.class);
        query = new ReadPatientByIdQuery(persistence);
    }
    
    private void thenAssertQueryThrowsPatientNotFoundException () {
        assertThrows(PatientNotFoundException.class, () -> query.execute(id));
    }
}
