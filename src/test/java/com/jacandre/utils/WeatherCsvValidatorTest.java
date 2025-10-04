package com.jacandre.utils;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(MockitoExtension.class)
public class WeatherCsvValidatorTest {

    private final WeatherCsvValidator validator = new WeatherCsvValidator();

    @Test
    void validateParsedRow_shouldReturnTrue_forValidRow() {
        String[] row = {"2025-10-01T00:00Z", "-3.48", "50.73", "275.2"};
        assertTrue(validator.validateParsedRow(row));
    }

    @Test
    void validateParsedRow_shouldReturnFalse_forInvalidDate() {
        String[] row = {"not-a-date", "-3.48", "50.73", "275.2"};
        assertFalse(validator.validateParsedRow(row));
    }

    @Test
    void validateParsedRow_shouldReturnFalse_forNonNumericTemperature() {
        String[] row = {"2025-10-01T00:00Z", "-3.48", "50.73", "hot"};
        assertFalse(validator.validateParsedRow(row));
    }

    @Test
    void validateParsedRow_shouldReturnFalse_forMissingFields() {
        String[] row = {"2025-10-01T00:00Z", "-3.48", "50.73"};
        assertFalse(validator.validateParsedRow(row));
    }

    @Test
    void validateParsedRow_shouldReturnFalse_forNullRow() {
        assertFalse(validator.validateParsedRow(null));
    }

    @Test
    void validateParsedRow_shouldReturnFalse_forEmptyStrings() {
        String[] row = {"", "", "", ""};
        assertFalse(validator.validateParsedRow(row));
    }

    @Test
    void validateParsedRow_shouldReturnFalse_forExtraFields() {
        String[] row = {"2025-10-01T00:00Z", "-3.48", "50.73", "275.2", "extra"};
        assertFalse(validator.validateParsedRow(row));
    }
}
