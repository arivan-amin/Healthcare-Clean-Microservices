package com.arivanamin.healthcare.backend.core.infrastructure.aspects.configuration;

import com.arivanamin.healthcare.backend.core.infrastructure.aspects.SpringSchedulerLoggingAspect;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class SpringSchedulerLoggingAspectConfiguration {
    
    @Bean
    SpringSchedulerLoggingAspect schedulerLoggingAspect () {
        return new SpringSchedulerLoggingAspect();
    }
}
