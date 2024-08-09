package com.arivanamin.healthcare.core.domain.dates;

import java.time.LocalDate;
import java.time.LocalDateTime;

public interface DateTimeFormatter {
    
    String format (LocalDateTime dateTime);
    
    String format (LocalDate dateTime);
    
    LocalDateTime parseDateTime (String dateTimeText);
    
    LocalDate parseDate (String dateText);
}
