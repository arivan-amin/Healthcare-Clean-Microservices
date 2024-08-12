package com.arivanamin.healthcare.core.infrastructure.openapi;

import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.License;

public final class OpenApiDetails {
    
    private OpenApiDetails () {
    }
    
    public static Contact getOpenApiContactDetails () {
        Contact contact = new Contact();
        contact.setEmail("arivanamin@gmail.com");
        contact.setName("Arivan Amin");
        contact.setUrl("https://www.arivan-amin.com");
        return contact;
    }
    
    public static License getOpenApiLicence () {
        return new License().name("MIT License").url("https://choosealicense.com/licenses/mit/");
    }
    
    public static String getOpenApiTermsOfService () {
        return "https://www.arivan-amin.com/healthcare-management-microservices/terms";
    }
}
