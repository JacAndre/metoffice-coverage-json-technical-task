package com.jacandre.models;

import java.time.ZonedDateTime;

public record WeatherRecord(ZonedDateTime time, double longitude, double latitude, double temperature) {
}
