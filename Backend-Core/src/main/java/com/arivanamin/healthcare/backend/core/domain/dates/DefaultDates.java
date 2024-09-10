package com.arivanamin.healthcare.backend.core.domain.dates;

import java.time.LocalDateTime;

public interface DefaultDates {
    
    LocalDateTime getDefaultStartDate ();
    
    LocalDateTime getDefaultEndDate ();
}
