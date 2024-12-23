package com.arivanamin.healthcare.backend.base.domain.dates;

import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;

import static com.arivanamin.healthcare.backend.base.domain.config.CoreApplicationConfig.ZONE_ID;
import static java.time.Instant.ofEpochMilli;
import static java.time.LocalDateTime.ofInstant;
import static lombok.AccessLevel.PRIVATE;

@Slf4j
@NoArgsConstructor (access = PRIVATE)
public final class TimestampHelper {
    
    public static LocalDateTime toLocalDateTime (long timestamp) {
        LocalDateTime result = ofInstant(ofEpochMilli(timestamp), ZONE_ID);
        log.info("timestamp {} converted to LocalDateTime = {}", timestamp, result);
        return result;
    }
    
    public static Long toTimestamp (LocalDateTime dateTime) {
        Long result = dateTime.atZone(ZONE_ID)
            .toEpochSecond();
        log.info("LocalDateTime {} converted to timestamp = {}", dateTime, result);
        return result;
    }
}
