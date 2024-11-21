package com.arivanamin.healthcare.backend.core.application.observability;

import io.micrometer.observation.ObservationRegistry;
import io.micrometer.observation.aop.ObservedAspect;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class ObservabilityConfiguration {
    
    @Bean
    ObservedAspect observedAspect (ObservationRegistry registry) {
        return new ObservedAspect(registry);
    }
}
