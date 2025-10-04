package com.jacandre.coverage.mapper;

import com.jacandre.coverage.model.Axes;
import com.jacandre.coverage.model.Coverage;
import com.jacandre.coverage.model.Range;
import com.jacandre.model.WeatherRecord;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.ZonedDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
@ExtendWith(MockitoExtension.class)
public class CoverageMapperTest {

    @Test
    void map_shouldCorrectlyMapWeatherRecordFields_toCoverageAxesAndValues() {
        WeatherRecord record = new WeatherRecord(
                ZonedDateTime.parse("2025-10-01T00:00:00Z"),
                50.73,
                -3.48,
                275.2
        );

        Coverage coverage = new CoverageMapper().map(List.of(record));

        // Axes
        Axes axes = coverage.getDomain().getAxes();
        assertEquals(List.of(-3.48), axes.getX());
        assertEquals(List.of(50.73), axes.getY());
        assertEquals(List.of("2025-10-01T00:00:00Z"), axes.getT());

        // Range values
        Range range = coverage.getRanges().get("temperature");
        assertEquals(List.of(275.2), range.getValues());
        assertEquals(List.of("t", "y", "x"), range.getAxisNames());
        assertEquals(List.of(1, 1, 1), range.getShape());
    }
}
