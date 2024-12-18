package com.arivanamin.healthcare.backend.base.domain.audit;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class AuditEvent {
    
    private String serviceName;
    private String location;
    private String action;
    private String data;
    private LocalDateTime timestamp;
}

