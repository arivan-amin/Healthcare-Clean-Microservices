package com.arivanamin.healthcare.backend.patient.details;

import com.arivanamin.healthcare.backend.core.domain.testing.BaseUnitTest;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@Tag ("current")
@ExtendWith (SpringExtension.class)
@DataJpaTest
@AutoConfigureTestDatabase (replace = AutoConfigureTestDatabase.Replace.NONE)
@Slf4j
class JpaPatientPersistenceTest implements BaseUnitTest {
    
    @Autowired
    private PatientRepository repository;
    
    private JpaPatientPersistence persistence;
    
    @BeforeEach
    void setUp () {
        persistence = new JpaPatientPersistence(repository);
    }
    
    @Test
    void shouldReturnAllPatients () {
        // given
        log.info("persistence.findAll() = {}", persistence.findAll());
        // when
        
        // then
    }
}
