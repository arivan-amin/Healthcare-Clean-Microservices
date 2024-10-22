package com.arivanamin.healthcare.backend.patient.core.query;

import com.arivanamin.healthcare.backend.core.domain.testing.BaseUnitTest;
import com.arivanamin.healthcare.backend.patient.core.entity.Patient;
import com.arivanamin.healthcare.backend.patient.core.persistence.PatientPersistence;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@Tag ("current")
class ReadPatientsQueryTest implements BaseUnitTest {
    
    private final List<Patient> patients = List.of();
    private PatientPersistence mockPersistence;
    private ReadPatientsQuery query;
    
    @Test
    void shouldCallPersistenceFindAll () {
        givenQueryWithMockPersistence();
        whenQueryIsExecuted();
        thenVerifyQueryCallsFindAll();
    }
    
    private void givenQueryWithMockPersistence () {
        mockPersistence = mock(PatientPersistence.class);
        when(mockPersistence.findAll()).thenReturn(patients);
        query = new ReadPatientsQuery(mockPersistence);
    }
    
    private List<Patient> whenQueryIsExecuted () {
        return query.execute();
    }
    
    private void thenVerifyQueryCallsFindAll () {
        verify(mockPersistence, times(1)).findAll();
    }
    
    @Test
    void shouldReturnResultOfPersistenceFindAll () {
        givenQueryWithMockPersistence();
        List<Patient> result = whenQueryIsExecuted();
        assertThat(patients).isSameAs(result);
    }
}
