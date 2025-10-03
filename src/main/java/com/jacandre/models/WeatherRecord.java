package com.jacandre.models;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public record WeatherRecord(ZonedDateTime time, double longitude, double latitude, double temperature) {

    public static WeatherRecord parse(String[] row) {
        ZonedDateTime time = ZonedDateTime.parse(row[0], DateTimeFormatter.ISO_DATE_TIME);
        double longitude = Double.parseDouble(row[1]);
        double latitude = Double.parseDouble(row[2]);
        double temperature = Double.parseDouble(row[3]);

        return new WeatherRecord(time, longitude, latitude, temperature);
    }
}
