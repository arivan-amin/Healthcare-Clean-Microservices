package com.arivanamin.healthcare.backend.patient.infrastructure.scheduler;

import com.arivanamin.healthcare.backend.patient.domain.entity.Patient;
import com.arivanamin.healthcare.backend.patient.domain.persistence.PatientPersistence;
import com.github.javafaker.Faker;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jeasy.random.EasyRandom;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Component
@RequiredArgsConstructor
@Slf4j
public class DemoDataScheduler {
    
    private final WebClient webClient =
        WebClient.builder().baseUrl("http://localhost:8080/api/patients/v1/profiles").build();
    
    private final PatientPersistence persistence;
    
    EasyRandom random = new EasyRandom();
    Faker faker = new Faker();
    
    @Scheduled (fixedRate = 3000)
    public void sendRequests () {
        if (faker.bool().bool()) {
            sendGetAllPatientsRequest();
        }
        if (faker.bool().bool()) {
            sendGetPatientByIdRequest();
        }
        if (faker.bool().bool()) {
            sendCreatePatientRequest();
        }
        if (faker.bool().bool()) {
            sendUpdatePatientRequest();
        }
        if (faker.bool().bool()) {
            sendDeletePatientRequest();
        }
    }
    
    private void sendGetAllPatientsRequest () {
        Mono<String> response =
            webClient.get().uri("").retrieve().bodyToMono(String.class);
        response.subscribe();
    }
    
    private void sendGetPatientByIdRequest () {
        UUID id = persistence.getAllPatients().get(0).getId();
        Mono<String> response =
            webClient.get().uri("/{id}", id).retrieve().bodyToMono(String.class);
        response.subscribe();
    }
    
    private void sendCreatePatientRequest () {
        Mono<String> response =
            webClient.post().uri("").bodyValue(random.nextObject(Patient.class))
                .retrieve().bodyToMono(String.class);
        response.subscribe();
    }
    
    private void sendUpdatePatientRequest () {
        UUID id = persistence.getAllPatients().get(0).getId();
        Mono<String> response =
            webClient.put().uri("/{id}", id).bodyValue(random.nextObject(Patient.class))
                .retrieve().bodyToMono(String.class);
        response.subscribe();
    }
    private void sendDeletePatientRequest () {
        Mono<String> response =
            webClient.delete().uri("/v1/profiles/{id}", "f8c3de3d-1fea-4d7c-a8b0-29f63c4c3454")
                .retrieve().bodyToMono(String.class);
        
        response.subscribe();
    }
}
