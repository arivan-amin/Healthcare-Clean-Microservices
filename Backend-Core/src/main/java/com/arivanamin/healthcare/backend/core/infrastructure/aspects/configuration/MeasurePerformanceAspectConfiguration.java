package com.arivanamin.healthcare.backend.core.infrastructure.aspects.configuration;

import com.arivanamin.healthcare.backend.core.infrastructure.aspects.MeasurePerformanceAspect;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class MeasurePerformanceAspectConfiguration {
    
    @Bean
    MeasurePerformanceAspect measureTimeAspect () {
        return new MeasurePerformanceAspect();
    }
}