package com.arivanamin.healthcare.backend.audit.core.query;

import com.arivanamin.healthcare.backend.audit.core.persistence.AuditEventStorage;
import com.arivanamin.healthcare.backend.audit.core.util.AuditPeriod;
import com.arivanamin.healthcare.backend.base.domain.audit.AuditEvent;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class ReadAuditEventsQuery {
    
    private final AuditEventStorage storage;
    
    public List<AuditEvent> execute (AuditPeriod auditPeriod) {
        return storage.findAll(auditPeriod.getStart(), auditPeriod.getEnd());
    }
}
