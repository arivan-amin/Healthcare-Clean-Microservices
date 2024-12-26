package com.arivanamin.healthcare.backend.audit.application.consumer;

import com.arivanamin.healthcare.backend.audit.core.persistence.AuditEventStorage;
import com.arivanamin.healthcare.backend.base.domain.aspects.LogExecutionTime;
import com.arivanamin.healthcare.backend.base.domain.audit.AuditEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import static com.arivanamin.healthcare.backend.base.domain.audit.AuditTopics.API_AUDIT_TOPIC;

@Service
@RequiredArgsConstructor
@Slf4j
public class ApiAuditConsumer {
    
    private final AuditEventStorage storage;
    
    @KafkaListener (topics = API_AUDIT_TOPIC, groupId = "api-audit-group")
    @LogExecutionTime
    public void consumeAuditEvent (AuditEvent auditEvent) {
        saveToStorage(auditEvent);
    }
    
    private void saveToStorage (AuditEvent event) {
        String savedEventId = storage.create(event);
        log.info("savedEventId = {}", savedEventId);
    }
}
