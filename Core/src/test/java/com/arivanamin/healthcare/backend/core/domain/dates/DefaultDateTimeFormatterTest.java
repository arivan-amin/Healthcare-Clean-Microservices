package com.arivanamin.healthcare.backend.core.domain.dates;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DefaultDateTimeFormatterTest {
    
    private DefaultDateTimeFormatter formatter;
    
    @BeforeEach
    void setUp () {
        formatter = new DefaultDateTimeFormatter();
    }
    
    @Test
    void formatsLocalDateTimeCorrectly () {
        LocalDateTime dateTime = LocalDateTime.of(2023, 7, 5, 12, 55);
        String result = formatter.format(dateTime);
        assertEquals("2023-07-05 12:55", result);
    }
    
    @Test
    void formatsLocalDateCorrectly () {
        LocalDate date = LocalDate.of(2023, 7, 5);
        String result = formatter.format(date);
        assertEquals("2023-07-05", result);
    }
    
    @Test
    void parsesLocalDateTimeCorrectly () {
        LocalDateTime dateTime = LocalDateTime.of(2023, 7, 5, 12, 55);
        String dateTimeText = formatter.format(dateTime);
        LocalDateTime result = formatter.parseDateTime(dateTimeText);
        assertEquals(dateTime, result);
    }
    
    @Test
    void parsesLocalDateCorrectly () {
        LocalDate dateTime = LocalDate.of(2023, 7, 5);
        String dateText = formatter.format(dateTime);
        LocalDate result = formatter.parseDate(dateText);
        assertEquals(dateTime, result);
    }
}
