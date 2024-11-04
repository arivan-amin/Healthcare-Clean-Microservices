package com.arivanamin.healthcare.backend.core.infrastructure.aspects;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.util.List;

import static com.arivanamin.healthcare.backend.core.domain.aspects.ExecuteAndLogPerformance.executeThrowable;
import static com.arivanamin.healthcare.backend.core.domain.base.CoreApplicationConfig.BASE_PACKAGE;

@Aspect
@Component
@Slf4j
public class MeasurePerformanceAspect {
    
    @Around ("@annotation(" + BASE_PACKAGE + ".core.domain.aspects.LogExecutionTime)")
    public Object logExecutionTimeOfMethod (ProceedingJoinPoint joinPoint) throws Throwable {
        List<Object> args = List.of(joinPoint.getArgs());
        
        log.info("Called method: {}, with parameters: {}", joinPoint.getSignature(), args);
        
        String methodName = joinPoint.getSignature().toShortString();
        return executeThrowable(methodName, joinPoint::proceed);
    }
}
