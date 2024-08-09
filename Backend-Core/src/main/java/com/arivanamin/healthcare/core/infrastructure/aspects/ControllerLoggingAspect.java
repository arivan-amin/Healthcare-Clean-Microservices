package com.arivanamin.healthcare.core.infrastructure.aspects;

import lombok.RequiredArgsConstructor;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

import static com.arivanamin.healthcare.core.domain.aspects.ExecuteAndLogPerformance.executeThrowable;

@Aspect
@RequiredArgsConstructor
public class ControllerLoggingAspect {
    
    @Around ("""
            @annotation(org.springframework.web.bind.annotation.GetMapping)
            or @annotation(org.springframework.web.bind.annotation.DeleteMapping)
            or @annotation(org.springframework.web.bind.annotation.PutMapping)
            or @annotation(org.springframework.web.bind.annotation.PostMapping)
            or @annotation(org.springframework.web.bind.annotation.PatchMapping)
        """)
    public Object logEndpoint (ProceedingJoinPoint joinPoint) throws Throwable {
        String name = "controller endpoint %s ".formatted(joinPoint.getSignature());
        return executeThrowable(name, joinPoint::proceed);
    }
}
