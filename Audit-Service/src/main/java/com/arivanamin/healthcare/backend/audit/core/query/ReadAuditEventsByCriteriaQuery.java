package com.arivanamin.healthcare.backend.audit.core.query;

import com.arivanamin.healthcare.backend.audit.core.persistence.AuditEventStorage;
import com.arivanamin.healthcare.backend.base.domain.audit.AuditEvent;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
public class ReadAuditEventsByCriteriaQuery {
    
    private final AuditEventStorage storage;
    
    public List<AuditEvent> execute (LocalDateTime start, LocalDateTime end, AuditEvent criteria) {
        return storage.findAllByCriteria(start, end, criteria);
    }
}
