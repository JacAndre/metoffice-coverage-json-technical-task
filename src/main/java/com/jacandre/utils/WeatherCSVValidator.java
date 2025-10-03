package com.jacandre.utils;

import lombok.extern.slf4j.Slf4j;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Arrays;

@Slf4j
public class WeatherCSVValidator {
    public boolean validateParsedRow(String[] row) {
        if (row == null || row.length != 4) return false;

        try {
            // Check if Time field is a valid ZonedDateTime
            ZonedDateTime.parse(row[0], DateTimeFormatter.ISO_DATE_TIME);

            // Check if remaining fields are valid doubles
            Double.parseDouble(row[1]); // longitude
            Double.parseDouble(row[2]); // latitude
            Double.parseDouble(row[3]); // temperature

            return true;
        } catch(DateTimeParseException | NumberFormatException e) {
            log.error("WeatherCSVValidator::validateParsedRow - Validation failed for row {}: {}", Arrays.toString(row), e.getMessage());
            return false;
        }
    }
}
