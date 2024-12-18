package com.arivanamin.healthcare.backend.patient.application.request;

import com.arivanamin.healthcare.backend.patient.core.entity.Patient;
import com.arivanamin.healthcare.backend.testing.architecture.bases.BaseUnitTest;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class CreatePatientRequestTest implements BaseUnitTest {
    
    @Test
    void shouldMapRequestToEntityCorrectly () {
        // given
        CreatePatientRequest request = RANDOM.nextObject(CreatePatientRequest.class);
        
        // when
        Patient entity = request.toEntity();
        
        // then
        assertThat(request.getFirstName()).isEqualTo(entity.getFirstName());
        assertThat(request.getLastName()).isEqualTo(entity.getLastName());
        assertThat(request.getEmail()).isEqualTo(entity.getEmail());
        assertThat(request.getDateOfBirth()).isEqualTo(entity.getDateOfBirth());
        assertThat(request.getGender()).isEqualTo(entity.getGender());
        assertThat(request.getAddress()).isEqualTo(entity.getAddress());
    }
}
