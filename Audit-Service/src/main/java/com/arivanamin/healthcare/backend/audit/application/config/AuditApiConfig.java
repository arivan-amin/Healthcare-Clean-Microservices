package com.arivanamin.healthcare.backend.audit.application.config;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor (access = AccessLevel.PRIVATE)
public final class AuditApiConfig {
    
    public static final String PROTECTED_API_BASE_PATH = "/audits/protected";
    public static final String PUBLIC_API_BASE_PATH = "/audits/public";
}
