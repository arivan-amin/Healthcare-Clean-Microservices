package com.arivanamin.healthcare.core.domain.dates;

import com.arivanamin.healthcare.core.domain.base.CoreApplicationConfig;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;

import static java.time.Instant.ofEpochMilli;
import static java.time.LocalDateTime.ofInstant;

@Slf4j
public final class TimestampHelper {
    
    private TimestampHelper () {
    }
    
    public static LocalDateTime convertEpochToLocalDateTime (long timestamp) {
        return ofInstant(ofEpochMilli(timestamp), CoreApplicationConfig.ZONE_ID);
    }
}
