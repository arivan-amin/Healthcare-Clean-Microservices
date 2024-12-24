package com.arivanamin.healthcare.backend.audit.storage;

import com.arivanamin.healthcare.backend.base.domain.audit.AuditEvent;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.modelmapper.ModelMapper;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document (value = "events")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class JpaAuditEvent {
    
    @Id
    String id;
    
    @NotBlank
    private String serviceName;
    
    @NotBlank
    private String location;
    
    @NotBlank
    private String action;
    
    @NotBlank
    private String data;
    
    @NotBlank
    private LocalDateTime timestamp;
    
    public static JpaAuditEvent fromDomain (AuditEvent event) {
        return new ModelMapper().map(event, JpaAuditEvent.class);
    }
    
    public AuditEvent toDomain () {
        return new ModelMapper().map(this, AuditEvent.class);
    }
}
