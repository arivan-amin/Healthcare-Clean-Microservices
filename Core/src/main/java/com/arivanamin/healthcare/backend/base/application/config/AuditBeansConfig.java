package com.arivanamin.healthcare.backend.base.application.config;

import com.arivanamin.healthcare.backend.base.application.audit.KafkaAuditEventPublisher;
import com.arivanamin.healthcare.backend.base.domain.audit.AuditEvent;
import com.arivanamin.healthcare.backend.base.domain.audit.AuditEventPublisher;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
class AuditBeansConfig {
    
    @Value ("${spring.application.name}")
    String applicationName;
    
    @Bean
    public AuditEvent auditEvent () {
        return AuditEvent.builder()
            .serviceName(applicationName)
            .timestamp(LocalDateTime.now())
            .build();
    }
    
    @Bean
    public AuditEventPublisher publisher (KafkaTemplate<String, AuditEvent> kafkaTemplate) {
        return new KafkaAuditEventPublisher(kafkaTemplate);
    }
}
