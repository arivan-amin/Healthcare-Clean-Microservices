package com.arivanamin.healthcare.backend.patient.infrastructure.mapper;

import com.arivanamin.healthcare.backend.core.domain.testing.BaseUnitTest;
import com.arivanamin.healthcare.backend.patient.application.presenter.PatientPresenter;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

class PatientPresenterTest implements BaseUnitTest {
    
    private PatientPresenter mapper;
    
    @BeforeEach
    void setUp () {
        mapper = new PatientPresenter();
    }
    
    @AfterEach
    void tearDown () {
    }
}
