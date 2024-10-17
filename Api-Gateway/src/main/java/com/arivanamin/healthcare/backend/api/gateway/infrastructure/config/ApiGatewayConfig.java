package com.arivanamin.healthcare.backend.api.gateway.infrastructure.config;

import org.springframework.cloud.gateway.route.Route;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Function;

import static java.lang.System.getenv;

@Configuration
public class ApiGatewayConfig {
    
    public static final String EUREKA_HOST = getenv().getOrDefault("EUREKA_HOST", "localhost");
    
    public static final String EUREKA_URL = "http://%s:8761".formatted(EUREKA_HOST);
    
    @Bean
    public RouteLocator routeLocator (RouteLocatorBuilder builder) {
        return builder.routes()
            .route(getDiscoveryServerRoute())
            .route(getDiscoveryServerStaticResourcesRoute())
            .route(getPatientServiceRoute())
            .route(getPatientApiDocRoute())
            .route(getPatientActuatorRoute())
            .build();
    }
    
    private Function<PredicateSpec, Buildable<Route>> getDiscoveryServerRoute () {
        return r -> r.path("/eureka/web").filters(f -> f.setPath("/")).uri(EUREKA_URL);
    }
    
    private Function<PredicateSpec, Buildable<Route>> getDiscoveryServerStaticResourcesRoute () {
        return r -> r.path("/eureka/**").uri(EUREKA_URL);
    }
    
    private Function<PredicateSpec, Buildable<Route>> getPatientServiceRoute () {
        return r -> r.path("/patient-service/**", "/api/patients/**").uri("lb://patient-service");
    }
    
    private Function<PredicateSpec, Buildable<Route>> getPatientApiDocRoute () {
        return r -> r.path("/patient-service/**")
            .filters(f -> f.setPath("/api-docs"))
            .uri("lb://patient-service");
    }
    
    private Function<PredicateSpec, Buildable<Route>> getPatientActuatorRoute () {
        return r -> r.path("/patients/actuator/**")
            .filters(f -> f.setPath("/actuator"))
            .uri("lb://patient-service");
    }
}
