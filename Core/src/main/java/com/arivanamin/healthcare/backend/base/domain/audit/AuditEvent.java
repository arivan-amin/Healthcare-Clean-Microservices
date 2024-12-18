package com.arivanamin.healthcare.backend.base.domain.audit;

import lombok.*;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuditEvent {
    
    private String serviceName;
    private String location;
    private String action;
    private String data;
    private LocalDateTime timestamp;
}

