package com.arivanamin.healthcare.backend.patient.details;

import com.arivanamin.healthcare.backend.patient.core.entity.Patient;
import com.arivanamin.healthcare.backend.testing.architecture.bases.BaseIntegrationTest;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@Tag ("current")
@DataJpaTest
@Slf4j
class JpaPatientPersistenceIntegrationTest implements BaseIntegrationTest {
    
    @Autowired
    private PatientRepository repository;
    
    private JpaPatientPersistence persistence;
    private int numberOfSavedEntities;
    
    private UUID actualId;
    private JpaPatient actualPatient;
    private List<Patient> resultPatients;
    private Patient resultPatient;
    
    @BeforeEach
    void setUp () {
        persistence = new JpaPatientPersistence(repository);
    }
    
    @Test
    void shouldReturnAllPatientsWhenFindAllIsCalled () {
        givenRepositoryWithSavedPatients();
        whenFindAllIsCalled();
        thenAssertThatAllEntitiesOfRepositoryAreReturned(resultPatients);
    }
    
    private void givenRepositoryWithSavedPatients () {
        numberOfSavedEntities = FAKER.number().numberBetween(3, 10);
        for (int i = 0; i < numberOfSavedEntities; i++) {
            repository.save(RANDOM.nextObject(JpaPatient.class));
        }
    }
    
    private void whenFindAllIsCalled () {
        resultPatients = persistence.findAll();
    }
    
    private void thenAssertThatAllEntitiesOfRepositoryAreReturned (List<Patient> result) {
        assertThat(result.size()).isEqualTo(numberOfSavedEntities);
    }
    
    @Test
    void shouldReturnSinglePatientWhenFindByIdIsCalled () {
        givenRepositoryWithSamplePatientsAndOnePatientExtracted();
        whenFindByIdIsCalled(actualId);
        thenAssertThatCorrectPatientIsReturned(resultPatient);
    }
    
    private void givenRepositoryWithSamplePatientsAndOnePatientExtracted () {
        givenRepositoryWithSavedPatients();
        actualId = repository.findAll()
            .get(FAKER.number().numberBetween(0, numberOfSavedEntities))
            .getId();
        actualPatient = repository.findAll()
            .stream()
            .filter(jpaPatient -> jpaPatient.getId().equals(actualId))
            .findFirst()
            .orElseThrow();
    }
    
    private void whenFindByIdIsCalled (UUID sampleId) {
        resultPatient = persistence.findById(sampleId).orElseThrow();
    }
    
    private void thenAssertThatCorrectPatientIsReturned (Patient resultPatient) {
        assertThat(resultPatient).isEqualTo(actualPatient.toDomain());
    }
    
    @Test
    void shouldCreatePatientWhenCreateIsCalled () {
        // given
        
        // when
        
        // then
    }
}
