package com.arivanamin.healthcare.backend.audit.storage;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface AuditEventRepository extends MongoRepository<JpaAuditEvent, String> {
    
    List<JpaAuditEvent> findAllByTimestampBetween (LocalDateTime start, LocalDateTime end);
}
