package com.arivanamin.healthcare.backend.testing.architecture.bases;

import com.github.javafaker.Faker;
import org.jeasy.random.EasyRandom;

public interface BaseUnitTest {
    
    Faker FAKER = new Faker();
    
    EasyRandom RANDOM = new EasyRandom();
}
