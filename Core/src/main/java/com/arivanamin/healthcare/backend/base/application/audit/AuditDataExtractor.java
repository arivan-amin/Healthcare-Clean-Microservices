package com.arivanamin.healthcare.backend.base.application.audit;

import com.arivanamin.healthcare.backend.base.domain.audit.AuditEvent;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.AnnotatedElement;
import java.lang.reflect.Method;
import java.util.List;

@RequiredArgsConstructor
public class AuditDataExtractor {
    
    private final AuditEvent event;
    
    public AuditEvent extractAuditData (ProceedingJoinPoint joinPoint) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        String requestURL = extractRequestUrl(method);
        String requestAnnotation = extractRequestAnnotation(method);
        event.setLocation(requestURL);
        event.setAction(requestAnnotation);
        event.setData(List.of(joinPoint.getArgs())
            .toString());
        return event;
    }
    
    private String extractRequestUrl (Method method) {
        if (method.isAnnotationPresent(GetMapping.class)) {
            return extractUrl(method.getAnnotation(GetMapping.class)
                .value());
        }
        else if (method.isAnnotationPresent(PostMapping.class)) {
            return extractUrl(method.getAnnotation(PostMapping.class)
                .value());
        }
        else if (method.isAnnotationPresent(PutMapping.class)) {
            return extractUrl(method.getAnnotation(PutMapping.class)
                .value());
        }
        else if (method.isAnnotationPresent(DeleteMapping.class)) {
            return extractUrl(method.getAnnotation(DeleteMapping.class)
                .value());
        }
        else if (method.isAnnotationPresent(PatchMapping.class)) {
            return extractUrl(method.getAnnotation(PatchMapping.class)
                .value());
        }
        return "";
    }
    
    private String extractRequestAnnotation (AnnotatedElement method) {
        if (method.isAnnotationPresent(GetMapping.class)) {
            return "Read";
        }
        else if (method.isAnnotationPresent(PostMapping.class)) {
            return "Create";
        }
        else if (method.isAnnotationPresent(PutMapping.class)) {
            return "Update";
        }
        else if (method.isAnnotationPresent(DeleteMapping.class)) {
            return "Delete";
        }
        else if (method.isAnnotationPresent(PatchMapping.class)) {
            return "Patch";
        }
        return "";
    }
    
    private String extractUrl (String[] paths) {
        if (paths != null && paths.length > 0) {
            return String.join(", ", paths);
        }
        return "Unknown URL";
    }
}
