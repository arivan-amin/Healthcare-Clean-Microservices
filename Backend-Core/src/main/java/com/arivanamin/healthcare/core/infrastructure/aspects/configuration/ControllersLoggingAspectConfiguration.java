package com.arivanamin.healthcare.core.infrastructure.aspects.configuration;

import com.arivanamin.healthcare.core.infrastructure.aspects.ControllerLoggingAspect;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class ControllersLoggingAspectConfiguration {
    
    @Bean
    ControllerLoggingAspect endpointsLoggingAspect () {
        return new ControllerLoggingAspect();
    }
}
