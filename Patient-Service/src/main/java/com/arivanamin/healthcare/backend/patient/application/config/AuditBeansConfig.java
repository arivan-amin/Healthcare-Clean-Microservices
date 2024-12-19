package com.arivanamin.healthcare.backend.patient.application.config;

import com.arivanamin.healthcare.backend.base.domain.audit.AuditEvent;
import com.arivanamin.healthcare.backend.patient.application.audit.KafkaAuditEventPublisher;
import com.arivanamin.healthcare.backend.patient.core.audit.AuditEventPublisher;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
class AuditBeansConfig {
    
    @Bean
    public AuditEvent persistence () {
        return AuditEvent.builder()
            .serviceName("transaction-service")
            .timestamp(LocalDateTime.now())
            .build();
    }
    
    @Bean
    public AuditEventPublisher publisher (KafkaTemplate<String, AuditEvent> kafkaTemplate) {
        return new KafkaAuditEventPublisher(kafkaTemplate);
    }
}
