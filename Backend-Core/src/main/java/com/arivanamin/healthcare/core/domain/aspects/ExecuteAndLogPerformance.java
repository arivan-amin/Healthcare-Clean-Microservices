package com.arivanamin.healthcare.core.domain.aspects;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public final class ExecuteAndLogPerformance {
    
    private ExecuteAndLogPerformance () {
    }
    
    public static <T> T execute (String methodName, Executor<T> executor) {
        final T result;
        final var start = System.currentTimeMillis();
        try {
            result = executor.execute();
        }
        finally {
            final var end = System.currentTimeMillis();
            logMethodPerformance(methodName, end, start);
        }
        return result;
    }
    
    private static void logMethodPerformance (String methodName, long end, long start) {
        log.info("execution of {} took {}ms", methodName, end - start);
    }
    
    public static <T> T executeThrowable (String methodName, ThrowableExecutor<T> executor)
        throws Throwable {
        final T result;
        final var start = System.currentTimeMillis();
        try {
            result = executor.execute();
        }
        finally {
            final var end = System.currentTimeMillis();
            logMethodPerformance(methodName, end, start);
        }
        return result;
    }
    
    @FunctionalInterface
    public interface Executor<T> {
        
        T execute ();
    }
    
    @FunctionalInterface
    public interface ThrowableExecutor<T> {
        
        T execute () throws Throwable;
    }
}
