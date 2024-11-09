package com.arivanamin.healthcare.backend.core.domain.dates;

import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;

import static com.arivanamin.healthcare.backend.core.domain.base.CoreApplicationConfig.ZONE_ID;
import static java.time.Instant.ofEpochMilli;
import static java.time.LocalDateTime.ofInstant;
import static lombok.AccessLevel.PRIVATE;

@Slf4j
@NoArgsConstructor (access = PRIVATE)
public final class TimestampHelper {
    
    public static LocalDateTime convertEpochToLocalDateTime (long timestamp) {
        log.info("received timestamp to convert = {}", timestamp);
        LocalDateTime result = ofInstant(ofEpochMilli(timestamp), ZONE_ID);
        log.info("timestamp resulted in LocalDateTime = {}", result);
        return result;
    }
}
