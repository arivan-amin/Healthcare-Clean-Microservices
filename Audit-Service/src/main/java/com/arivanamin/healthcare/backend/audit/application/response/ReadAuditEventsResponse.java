package com.arivanamin.healthcare.backend.audit.application.response;

import com.arivanamin.healthcare.backend.base.domain.audit.AuditEvent;
import lombok.Value;

import java.util.List;

@Value
public class ReadAuditEventsResponse {
    
    List<AuditEventResponse> responses;
    
    public static ReadAuditEventsResponse of (List<AuditEvent> patients) {
        return new ReadAuditEventsResponse(patients.stream()
            .map(AuditEventResponse::of)
            .toList());
    }
}
