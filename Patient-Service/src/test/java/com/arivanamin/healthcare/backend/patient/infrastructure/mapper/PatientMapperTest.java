package com.arivanamin.healthcare.backend.patient.infrastructure.mapper;

import com.arivanamin.healthcare.backend.core.domain.testing.BaseUnitTest;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

class PatientMapperTest implements BaseUnitTest {
    
    private PatientMapper mapper;
    
    @BeforeEach
    void setUp () {
        mapper = new PatientMapper();
    }
    
    @AfterEach
    void tearDown () {
    }
}
