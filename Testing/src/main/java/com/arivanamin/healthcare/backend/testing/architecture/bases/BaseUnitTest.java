package com.arivanamin.healthcare.backend.testing.architecture.bases;

import com.github.javafaker.Faker;
import org.jeasy.random.EasyRandom;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@SuppressWarnings ("NewClassNamingConvention")
@ExtendWith (MockitoExtension.class)
public interface BaseUnitTest {
    
    Faker FAKER = new Faker();
    
    EasyRandom RANDOM = new EasyRandom();
}
