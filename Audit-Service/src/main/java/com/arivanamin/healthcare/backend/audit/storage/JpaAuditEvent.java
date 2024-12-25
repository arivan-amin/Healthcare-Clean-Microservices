package com.arivanamin.healthcare.backend.audit.storage;

import com.arivanamin.healthcare.backend.base.domain.audit.AuditEvent;
import jakarta.persistence.Id;
import lombok.*;
import org.modelmapper.ModelMapper;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class JpaAuditEvent {
    
    @Id
    String id;
    
    private String serviceName;
    private String location;
    private String action;
    private String data;
    private LocalDateTime timestamp;
    
    public static JpaAuditEvent fromDomain (AuditEvent event) {
        return new ModelMapper().map(event, JpaAuditEvent.class);
    }
    
    public AuditEvent toDomain () {
        return new ModelMapper().map(this, AuditEvent.class);
    }
}
