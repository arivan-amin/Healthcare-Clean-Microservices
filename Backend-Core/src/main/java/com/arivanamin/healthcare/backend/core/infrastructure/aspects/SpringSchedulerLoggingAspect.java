package com.arivanamin.healthcare.backend.core.infrastructure.aspects;

import com.arivanamin.healthcare.backend.core.domain.aspects.ExecuteAndLogPerformance;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

@Aspect
public class SpringSchedulerLoggingAspect {
    
    @Around ("@annotation(org.springframework.scheduling.annotation.Scheduled)")
    public Object logScheduler (ProceedingJoinPoint joinPoint) throws Throwable {
        return ExecuteAndLogPerformance.executeThrowable(initJobName(joinPoint),
            joinPoint::proceed);
    }
    
    private String initJobName (JoinPoint joinPoint) {
        final String classPath = joinPoint.getSignature().getDeclaringTypeName();
        return "The scheduled task: %s".formatted(classPath);
    }
}
