package com.arivanamin.healthcare.backend.patient.details;

import com.arivanamin.healthcare.backend.patient.core.entity.Patient;
import com.arivanamin.healthcare.backend.testing.architecture.bases.BaseUnitTest;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@Tag ("current")
@ExtendWith (SpringExtension.class)
@DataJpaTest
@Slf4j
class JpaPatientPersistenceTest implements BaseUnitTest {
    
    @Autowired
    private PatientRepository repository;
    
    private JpaPatientPersistence persistence;
    private int numberOfSavedEntities;
    
    @BeforeEach
    void setUp () {
        persistence = new JpaPatientPersistence(repository);
    }
    
    @Test
    void shouldReturnAllPatients () {
        givenRepositoryWithSavedPatients();
        List<Patient> result = whenFindAllIsCalled();
        thenAllTheEntitiesOfRepositoryIsReturned(result);
    }
    
    private void givenRepositoryWithSavedPatients () {
        numberOfSavedEntities = FAKER.number().numberBetween(3, 10);
        for (int i = 0; i < numberOfSavedEntities; i++) {
            repository.save(RANDOM.nextObject(JpaPatient.class));
        }
    }
    
    private List<Patient> whenFindAllIsCalled () {
        return persistence.findAll();
    }
    
    private void thenAllTheEntitiesOfRepositoryIsReturned (List<Patient> result) {
        assertThat(result.size()).isEqualTo(numberOfSavedEntities);
    }
}
