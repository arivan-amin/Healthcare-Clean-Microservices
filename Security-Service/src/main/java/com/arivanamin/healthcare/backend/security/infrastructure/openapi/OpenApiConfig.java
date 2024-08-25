package com.arivanamin.healthcare.backend.security.infrastructure.openapi;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

import static com.arivanamin.healthcare.core.infrastructure.openapi.OpenApiDetails.*;

@Configuration
public class OpenApiConfig {
    
    @Bean
    public OpenAPI myOpenAPI () {
        Server server = new Server();
        server.setUrl("localhost:8080/protected/security");
        server.setDescription("Server URL");
        
        Info info = new Info().title("Backend Security API")
            .description("Provides all the API related to Security service").version("1.0")
            .contact(getOpenApiContactDetails()).termsOfService(getOpenApiTermsOfService())
            .license(getOpenApiLicence());
        
        return new OpenAPI().info(info).servers(List.of(server));
    }
}