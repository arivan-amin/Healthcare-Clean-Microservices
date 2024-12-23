package com.arivanamin.healthcare.backend.audit.application.consumer;

import com.arivanamin.healthcare.backend.base.domain.audit.AuditEvent;
import com.arivanamin.healthcare.backend.base.domain.audit.AuditTopics;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class AuditConsumerService {
    
    @KafkaListener (topics = AuditTopics.API_AUDIT_TOPIC, groupId = "audit-service-group")
    public void consumeAuditEvent (AuditEvent auditEvent) {
        log.info("Received audit event: {}", auditEvent);
        // saveToDatabase(auditEvent);
    }
    
    private void saveToDatabase (AuditEvent auditEvent) {
        log.info("Audit event saved to storage: {}", auditEvent);
    }
}
