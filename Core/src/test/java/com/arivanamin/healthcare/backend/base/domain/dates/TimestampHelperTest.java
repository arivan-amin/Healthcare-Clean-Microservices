package com.arivanamin.healthcare.backend.base.domain.dates;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
class TimestampHelperTest {
    
    @Test
    void shouldConvertLocalDateTimeToTimestampInMilliseconds () {
        // given
        LocalDateTime dateTime = LocalDateTime.of(2019, 11, 15, 13, 15, 30);
        
        // when
        Long timestamp = TimestampHelper.toTimestampInMilliseconds(dateTime);
        
        // then
        assertThat(timestamp).isEqualTo(1573812930000L);
    }
    
    @Test
    void shouldConvertTimestampInMillisecondsToLocalDateTime () {
        // given
        long timestamp = 1573841730000L;
        
        // when
        LocalDateTime dateTime = TimestampHelper.toLocalDateTime(timestamp);
        
        // then
        assertThat(dateTime).isEqualTo(LocalDateTime.parse("2019-11-15T21:15:30"));
    }
}
