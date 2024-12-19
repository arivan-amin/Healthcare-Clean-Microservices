package com.arivanamin.healthcare.backend.patient.core.audit;

import com.arivanamin.healthcare.backend.base.domain.audit.AuditEvent;

@FunctionalInterface
public interface AuditEventPublisher {
    
    void sendAuditLog (String topic, AuditEvent event);
}
