package com.arivanamin.healthcare.backend.api.gateway.infrastructure.config;

import org.springframework.cloud.gateway.route.Route;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Function;

import static java.lang.System.getenv;

@Configuration
public class GatewayConfig {
    
    public static final String EUREKA_HOST = getenv().getOrDefault("EUREKA_HOST", "localhost");
    
    public static final String EUREKA_URL = "http://%s:8761".formatted(EUREKA_HOST);
    
    @Bean
    public RouteLocator routeLocator (RouteLocatorBuilder builder) {
        return builder.routes()
            .route(getDiscoveryServerRoute())
            .route(getDiscoveryServerStaticResourcesRoute())
            .route(getAppointmentServiceRoute())
            .route(getDoctorServiceRoute())
            .route(getNotificationServiceRoute())
            .route(getPatientServiceRoute())
            .route(getSecurityServiceRoute())
            .build();
    }
    
    private Function<PredicateSpec, Buildable<Route>> getDiscoveryServerRoute () {
        return r -> r.path("/eureka/web").filters(f -> f.setPath("/")).uri(EUREKA_URL);
    }
    
    private Function<PredicateSpec, Buildable<Route>> getDiscoveryServerStaticResourcesRoute () {
        return r -> r.path("/eureka/**").uri(EUREKA_URL);
    }
    
    private Function<PredicateSpec, Buildable<Route>> getAppointmentServiceRoute () {
        return r -> r.path("/appointment-service/**", "/api/appointments/**",
            "/appointments/actuator/**").uri("lb://appointment-service");
    }
    
    private Function<PredicateSpec, Buildable<Route>> getDoctorServiceRoute () {
        return r -> r.path("/doctor-service/**", "/api/doctors/**", "/doctors/actuator/**")
            .uri("lb://doctor-service");
    }
    
    private Function<PredicateSpec, Buildable<Route>> getNotificationServiceRoute () {
        return r -> r.path("/notification-service/**", "/api/notifications/**",
            "/notifications/actuator/**").uri("lb://notification-service");
    }
    
    private Function<PredicateSpec, Buildable<Route>> getPatientServiceRoute () {
        return r -> r.path("/patient-service/**", "/api/patients/**", "/patients/actuator/**")
            .uri("lb://patient-service");
    }
    
    private Function<PredicateSpec, Buildable<Route>> getSecurityServiceRoute () {
        return r -> r.path("/security-service/**", "/api/security/**", "/security/actuator/**")
            .uri("lb://security-service");
    }
}
