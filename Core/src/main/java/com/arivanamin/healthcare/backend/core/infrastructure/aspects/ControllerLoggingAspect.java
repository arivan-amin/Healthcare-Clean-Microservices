package com.arivanamin.healthcare.backend.core.infrastructure.aspects;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.util.List;

import static com.arivanamin.healthcare.backend.core.domain.aspects.ExecuteAndLogPerformance.executeThrowable;

@Aspect
@Component
@Slf4j
public class ControllerLoggingAspect {
    
    @Around ("""
            @annotation(org.springframework.web.bind.annotation.GetMapping)
            or @annotation(org.springframework.web.bind.annotation.DeleteMapping)
            or @annotation(org.springframework.web.bind.annotation.PutMapping)
            or @annotation(org.springframework.web.bind.annotation.PostMapping)
            or @annotation(org.springframework.web.bind.annotation.PatchMapping)
        """)
    public Object logEndpoint (ProceedingJoinPoint joinPoint) throws Throwable {
        List<Object> args = List.of(joinPoint.getArgs());
        
        log.info("Incoming request to: {}, with parameters: {}", joinPoint.getSignature(), args);
        
        String name = "controller endpoint %s ".formatted(joinPoint.getSignature());
        return executeThrowable(name, joinPoint::proceed);
    }
}
