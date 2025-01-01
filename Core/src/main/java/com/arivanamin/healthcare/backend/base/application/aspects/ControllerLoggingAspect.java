package com.arivanamin.healthcare.backend.base.application.aspects;

import com.arivanamin.healthcare.backend.base.application.audit.AuditDataExtractor;
import com.arivanamin.healthcare.backend.base.domain.audit.AuditEvent;
import com.arivanamin.healthcare.backend.base.domain.audit.AuditEventPublisher;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.util.List;

import static com.arivanamin.healthcare.backend.base.domain.aspects.ExecuteAndLogPerformance.executeThrowable;
import static com.arivanamin.healthcare.backend.base.domain.audit.AuditTopics.API_AUDIT_TOPIC;

@Aspect
@Component
@RequiredArgsConstructor
@Slf4j
class ControllerLoggingAspect {
    
    private final AuditEventPublisher publisher;
    
    private final AuditDataExtractor dataExtractor;
    
    // todo 1/1/25 - maybe duration of method call should be also recorded in audit event ?
    @Around ("""
            @annotation(org.springframework.web.bind.annotation.GetMapping)
            or @annotation(org.springframework.web.bind.annotation.PostMapping)
            or @annotation(org.springframework.web.bind.annotation.PutMapping)
            or @annotation(org.springframework.web.bind.annotation.DeleteMapping)
            or @annotation(org.springframework.web.bind.annotation.PatchMapping)
        """)
    public Object logEndpoint (ProceedingJoinPoint joinPoint) throws Throwable {
        log.info("Incoming request to: {}, with parameters: {}", joinPoint.getSignature(),
            List.of(joinPoint.getArgs()));
        
        sendAuditEventThroughPublisher(joinPoint);
        
        String name = "controller endpoint %s ".formatted(joinPoint.getSignature());
        return executeThrowable(name, joinPoint::proceed);
    }
    
    private void sendAuditEventThroughPublisher (ProceedingJoinPoint joinPoint) {
        AuditEvent event = dataExtractor.extractAuditData(joinPoint);
        publisher.sendAuditLog(API_AUDIT_TOPIC, event);
    }
}
