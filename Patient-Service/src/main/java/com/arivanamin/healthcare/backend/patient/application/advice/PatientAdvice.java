package com.arivanamin.healthcare.backend.patient.application.advice;

import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.net.URI;
import java.time.Instant;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.ProblemDetail.forStatusAndDetail;

@RestControllerAdvice
public class PatientAdvice {
    
    @ExceptionHandler (MissingServletRequestParameterException.class)
    ProblemDetail handleMissingParameterException (
        MissingServletRequestParameterException exception) {
        ProblemDetail detail = forStatusAndDetail(BAD_REQUEST, exception.getMessage());
        detail.setTitle("Bad Request, Missing Parameter");
        detail.setType(URI.create(
            "https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework" +
                "/web/bind/MissingServletRequestParameterException.html"));
        detail.setProperty("errorCategory", "Missing Parameter");
        detail.setProperty("timestamp", Instant.now());
        detail.setDetail(exception.getMessage());
        return detail;
    }
    
    @ExceptionHandler (Exception.class)
    ProblemDetail handleGeneralExceptions (Exception exception) {
        ProblemDetail detail = forStatusAndDetail(INTERNAL_SERVER_ERROR, exception.getMessage());
        detail.setTitle("General Error: ");
        detail.setType(URI.create(
            "https://docs.oracle.com/en/java/javase/21/docs/api/java.base/java/lang/Exception" +
                ".html"));
        detail.setProperty("errorCategory", "Internal Error");
        detail.setProperty("timestamp", Instant.now());
        detail.setDetail(exception.getMessage());
        return detail;
    }
}
