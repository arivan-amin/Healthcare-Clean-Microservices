package com.arivanamin.healthcare.backend.patient.core.query;

import com.arivanamin.healthcare.backend.core.domain.testing.BaseUnitTest;
import com.arivanamin.healthcare.backend.patient.core.persistence.PatientPersistence;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

@Tag ("current")
class ReadPatientsQueryTest implements BaseUnitTest {
    
    private PatientPersistence mockPersistence;
    private ReadPatientsQuery query;
    
    @Test
    void shouldCallPersistenceFindAll () {
        givenQueryWithMockPersistence();
        whenQueryIsExecuted();
        thenVerifyQueryCallsPersistenceFindAll();
    }
    
    private void givenQueryWithMockPersistence () {
        mockPersistence = mock(PatientPersistence.class);
        query = new ReadPatientsQuery(mockPersistence);
    }
    
    private void whenQueryIsExecuted () {
        query.execute();
    }
    
    private void thenVerifyQueryCallsPersistenceFindAll () {
        verify(mockPersistence, times(1)).findAll();
    }
}
