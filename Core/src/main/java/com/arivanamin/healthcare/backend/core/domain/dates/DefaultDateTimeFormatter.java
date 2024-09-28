package com.arivanamin.healthcare.backend.core.domain.dates;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static java.time.format.DateTimeFormatter.ofPattern;

public class DefaultDateTimeFormatter implements DateTimeFormatter {
    
    public static final String DATE_FORMAT = "yyyy-MM-dd";
    public static final String DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm";
    
    @Override
    public String format (LocalDateTime dateTime) {
        return dateTime.format(createPatternFromDateTimeFormat());
    }
    
    @Override
    public String format (LocalDate dateTime) {
        return dateTime.format(createPatternFromDateFormat());
    }
    
    @Override
    public LocalDateTime parseDateTime (String dateTimeText) {
        return LocalDateTime.parse(dateTimeText, createPatternFromDateTimeFormat());
    }
    
    @Override
    public LocalDate parseDate (String dateText) {
        return LocalDate.parse(dateText, createPatternFromDateFormat());
    }
    
    private static java.time.format.DateTimeFormatter createPatternFromDateFormat () {
        return ofPattern(DATE_FORMAT);
    }
    
    private static java.time.format.DateTimeFormatter createPatternFromDateTimeFormat () {
        return ofPattern(DATE_TIME_FORMAT);
    }
}
