package com.arivanamin.healthcare.backend.core.infrastructure.aspects;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

import static com.arivanamin.healthcare.backend.core.domain.aspects.ExecuteAndLogPerformance.executeThrowable;

@Aspect
@RequiredArgsConstructor
@Slf4j
public class MeasurePerformanceAspect {
    
    @Around ("@annotation(aspects.domain.com.arivanamin.healthcare.backend.core.LogExecutionTime)")
    public Object logExecutionTimeOfMethod (ProceedingJoinPoint joinPoint) throws Throwable {
        String methodName = joinPoint.getSignature().toShortString();
        return executeThrowable(methodName, joinPoint::proceed);
    }
}
