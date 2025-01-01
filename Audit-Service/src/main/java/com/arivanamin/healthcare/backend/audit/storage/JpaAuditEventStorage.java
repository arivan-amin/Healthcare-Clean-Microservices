package com.arivanamin.healthcare.backend.audit.storage;

import com.arivanamin.healthcare.backend.audit.core.persistence.AuditEventStorage;
import com.arivanamin.healthcare.backend.base.domain.audit.AuditEvent;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static com.arivanamin.healthcare.backend.audit.storage.JpaAuditEvent.fromDomain;
import static org.springframework.data.domain.ExampleMatcher.StringMatcher.CONTAINING;

@RequiredArgsConstructor
@Transactional
@Slf4j
public class JpaAuditEventStorage implements AuditEventStorage {
    
    private final AuditEventRepository repository;
    
    @Override
    public List<AuditEvent> findAll (LocalDateTime start, LocalDateTime end) {
        return repository.findAllByRecordedAtBetween(start, end)
            .stream()
            .map(JpaAuditEvent::toDomain)
            .toList();
    }
    
    private static ExampleMatcher getExampleMatcher () {
        return ExampleMatcher.matching()
            .withIgnorePaths("id", "recordedAt")
            .withIgnoreNullValues()
            .withIgnoreCase()
            .withStringMatcher(CONTAINING);
    }
    
    private static Example<JpaAuditEvent> createExampleFromEvent (AuditEvent event) {
        return Example.of(fromDomain(event), getExampleMatcher());
    }
    
    @Override
    public List<AuditEvent> findAllByCriteria (AuditEvent criteria) {
        return repository.findAll(createExampleFromEvent(criteria))
            .stream()
            .map(JpaAuditEvent::toDomain)
            .toList();
    }
    
    @Override
    public Optional<AuditEvent> findById (String id) {
        return repository.findById(id)
            .map(JpaAuditEvent::toDomain);
    }
    
    @Override
    public String create (AuditEvent event) {
        return repository.save(fromDomain(event))
            .getId();
    }
}
