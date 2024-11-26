package com.arivanamin.healthcare.backend.patient.details;

import com.arivanamin.healthcare.backend.testing.architecture.bases.BaseUnitTest;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@Tag ("current")
@ExtendWith (MockitoExtension.class)
class JpaPatientPersistenceTest implements BaseUnitTest {
    
    @Mock
    PatientRepository repository;
    
    @InjectMocks
    JpaPatientPersistence persistence;
    
    @Test
    void shouldReturnAllPatientsFromRepository () {
        // given
        
        // when
        
        // then
    }
}
