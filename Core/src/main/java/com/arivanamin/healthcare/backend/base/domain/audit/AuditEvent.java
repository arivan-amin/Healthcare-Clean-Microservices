package com.arivanamin.healthcare.backend.base.domain.audit;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuditEvent {
    
    private String serviceName;
    private String location;
    private String action;
    private String data;
    private long timestamp;
}

