package com.arivanamin.healthcare.core.domain.testing;

import com.github.javafaker.Faker;
import org.jeasy.random.EasyRandom;

public interface BaseUnitTest {
    
    Faker FAKER = new Faker();
    
    EasyRandom RANDOM = new EasyRandom();
}
