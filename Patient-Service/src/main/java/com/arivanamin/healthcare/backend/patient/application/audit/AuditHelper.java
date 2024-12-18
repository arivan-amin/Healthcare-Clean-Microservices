package com.arivanamin.healthcare.backend.patient.application.audit;

import com.arivanamin.healthcare.backend.base.domain.audit.AuditEvent;
import com.arivanamin.healthcare.backend.base.domain.audit.AuditTopics;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@RequiredArgsConstructor
@Service
public class AuditHelper {
    
    private final KafkaTemplate<String, AuditEvent> kafkaTemplate;
    
    public void sendAuditLog (String location, String data, String serviceName, String action) {
        AuditEvent event = AuditEvent.builder()
            .location(location)
            .data(data)
            .serviceName(serviceName)
            .timestamp(LocalDateTime.now())
            .action(action)
            .build();
        
        kafkaTemplate.send(AuditTopics.API_AUDIT_TOPIC, event);
    }
}
