package com.arivanamin.healthcare.backend.base.application.config;

import com.arivanamin.healthcare.backend.base.application.audit.AuditDataExtractor;
import com.arivanamin.healthcare.backend.base.application.audit.KafkaAuditEventPublisher;
import com.arivanamin.healthcare.backend.base.domain.audit.AuditEvent;
import com.arivanamin.healthcare.backend.base.domain.audit.AuditEventPublisher;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
class AuditBeansConfig {
    
    @Value ("${spring.application.name}")
    String applicationName;
    
    /*@Bean
    @Scope (value = SCOPE_REQUEST, proxyMode = TARGET_CLASS)
    public AuditEvent auditEvent () {
        return AuditEvent.builder()
            .serviceName(applicationName)
            .timestamp(TimestampHelper.toTimestamp(LocalDateTime.now()))
            .build();
    }*/
    
    @Bean
    public AuditDataExtractor auditDataExtractor () {
        return new AuditDataExtractor(applicationName);
    }
    
    @Bean
    public AuditEventPublisher publisher (KafkaTemplate<String, AuditEvent> kafkaTemplate) {
        return new KafkaAuditEventPublisher(kafkaTemplate);
    }
}
