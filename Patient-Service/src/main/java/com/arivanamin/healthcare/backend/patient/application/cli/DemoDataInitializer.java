package com.arivanamin.healthcare.backend.patient.application.cli;

import com.arivanamin.healthcare.backend.base.domain.gender.Gender;
import com.arivanamin.healthcare.backend.patient.core.entity.Patient;
import com.arivanamin.healthcare.backend.patient.core.persistence.PatientPersistence;
import com.github.javafaker.Faker;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.util.stream.IntStream;

import static java.time.LocalDate.now;

@Configuration
@RequiredArgsConstructor
class DemoDataInitializer {
    
    Faker faker = new Faker();
    
    @Bean
    CommandLineRunner initDatabase (PatientPersistence persistence) {
        return args -> {
            if (persistence.findAll().isEmpty()) {
                int numberOfEntities = faker.number().numberBetween(5, 15);
                populatePatientRepository(persistence, numberOfEntities);
            }
        };
    }
    
    private void populatePatientRepository (PatientPersistence persistence, int numberOfEntities) {
        IntStream.rangeClosed(1, numberOfEntities)
            .mapToObj(this::createPatient)
            .forEachOrdered(persistence::create);
    }
    
    private Patient createPatient (long i) {
        Patient patient = new Patient();
        patient.setFirstName(faker.elderScrolls().firstName());
        patient.setLastName(faker.elderScrolls().lastName());
        patient.setEmail(faker.internet().emailAddress());
        patient.setDateOfBirth(createDateOfBirth());
        patient.setGender(getGender());
        patient.setAddress(faker.address().fullAddress());
        return patient;
    }
    
    private LocalDate createDateOfBirth () {
        int yearsToSubtract = faker.number().numberBetween(18, 90);
        return now().minusYears(yearsToSubtract);
    }
    
    private Gender getGender () {
        return faker.bool().bool() ? Gender.MALE : Gender.FEMALE;
    }
}
