package com.arivanamin.healthcare.backend.audit.application.config;

import com.arivanamin.healthcare.backend.audit.core.persistence.AuditEventStorage;
import com.arivanamin.healthcare.backend.audit.storage.AuditEventRepository;
import com.arivanamin.healthcare.backend.audit.storage.JpaAuditEventStorage;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
class CommandAndQueryBeansConfig {
    
    @Bean
    public AuditEventStorage storage (AuditEventRepository repository) {
        return new JpaAuditEventStorage(repository);
    }
}
