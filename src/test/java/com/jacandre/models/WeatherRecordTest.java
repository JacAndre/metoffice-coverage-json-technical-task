package com.jacandre.models;

import org.junit.jupiter.api.Test;

import java.time.ZonedDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class WeatherRecordTest {

    @Test
    void parse_shouldReturnWeatherRecord_forValidRow() {
        String[] row = {"2025-10-01T00:00Z", "-3.48", "50.73", "275.2"};

        WeatherRecord record = WeatherRecord.parse(row);

        assertEquals(ZonedDateTime.parse("2025-10-01T00:00Z"), record.time());
        assertEquals(-3.48, record.longitude());
        assertEquals(50.73, record.latitude());
        assertEquals(275.2, record.temperature());
    }
}
