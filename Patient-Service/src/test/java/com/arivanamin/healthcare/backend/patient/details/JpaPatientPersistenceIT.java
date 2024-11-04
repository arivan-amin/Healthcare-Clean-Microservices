package com.arivanamin.healthcare.backend.patient.details;

import com.arivanamin.healthcare.backend.core.domain.testing.BaseUnitTest;
import com.arivanamin.healthcare.backend.patient.PatientApplication;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

@Tag ("current")
@ExtendWith (SpringExtension.class)
@SpringBootTest (classes = PatientApplication.class)
@Testcontainers
class JpaPatientPersistenceIT implements BaseUnitTest {
    
    @Container
    public static MySQLContainer<?> mysqlContainer =
        new MySQLContainer<>("mysql:9.0").withDatabaseName("patient_service")
            .withUsername("root")
            .withPassword("mysql");
    
    @Autowired
    private PatientRepository repository;
    
    private JpaPatientPersistence persistence;
    
    @DynamicPropertySource
    static void setDatasourceProperties (DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", mysqlContainer::getJdbcUrl);
        registry.add("spring.datasource.username", mysqlContainer::getUsername);
        registry.add("spring.datasource.password", mysqlContainer::getPassword);
    }
    
    @BeforeEach
    void setUp () {
        persistence = new JpaPatientPersistence(repository);
    }
    
    @AfterEach
    void tearDown () {
        repository.deleteAll();
    }
    
    @Test
    void should () {
        // given
        
        // when
        
        // then
    }
}
