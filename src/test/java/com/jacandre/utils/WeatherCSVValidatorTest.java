package com.jacandre.utils;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Slf4j
@ExtendWith(MockitoExtension.class)
public class WeatherCSVValidatorTest {
    @InjectMocks
    WeatherCSVValidator csvValidator;

    @Test
    void validateParsedRow_shouldReturnTrue_forValidRow() {
        String[] row = {"2025-10-01T00:00Z", "-3.48", "50.73", "275.2"};
        assertTrue(csvValidator.validateParsedRow(row));
    }

    @Test
    void validateParsedRow_shouldReturnFalse_forInvalidDate() {
        String[] row = {"not-a-date", "-3.48", "50.73", "275.2"};
        assertFalse(csvValidator.validateParsedRow(row));
    }

    @Test
    void validateParsedRow_shouldReturnFalse_forNonNumericTemperature() {
        String[] row = {"2025-10-01T00:00Z", "-3.48", "50.73", "hot"};
        assertFalse(csvValidator.validateParsedRow(row));
    }

    @Test
    void validateParsedRow_shouldReturnFalse_forMissingFields() {
        String[] row = {"2025-10-01T00:00Z", "-3.48", "50.73"};
        assertFalse(csvValidator.validateParsedRow(row));
    }

    @Test
    void validateParsedRow_shouldReturnFalse_forNullRow() {
        assertFalse(csvValidator.validateParsedRow(null));
    }
}
