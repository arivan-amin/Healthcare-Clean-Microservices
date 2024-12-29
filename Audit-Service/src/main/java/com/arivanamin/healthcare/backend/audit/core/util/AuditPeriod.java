package com.arivanamin.healthcare.backend.audit.core.util;

import com.arivanamin.healthcare.backend.base.domain.dates.TimestampHelper;
import lombok.AllArgsConstructor;
import lombok.Value;

import java.time.LocalDateTime;

@Value
@AllArgsConstructor
public class AuditPeriod {
    
    LocalDateTime start;
    LocalDateTime end;
    
    public static AuditPeriod of (long startTimestamp, long endTimestamp) {
        LocalDateTime start = TimestampHelper.toLocalDateTime(startTimestamp);
        LocalDateTime end = TimestampHelper.toLocalDateTime(endTimestamp);
        return new AuditPeriod(start, end);
    }
}
