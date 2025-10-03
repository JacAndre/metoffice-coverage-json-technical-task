package com.jacandre.utils;

import com.jacandre.model.WeatherRecord;

import java.time.format.DateTimeFormatter;
import java.util.List;

public class WeatherRecordMapperHelper {

    public static List<Double> extractLatitudes(List<WeatherRecord> records) {
        return records.stream()
                .map(WeatherRecord::latitude)
                .toList();
    }

    public static List<Double> extractLongitudes(List<WeatherRecord> records) {
        return records.stream()
                .map(WeatherRecord::longitude)
                .toList();
    }

    public static List<Double> extractTemperatures(List<WeatherRecord> records) {
        return records.stream()
                .map(WeatherRecord::temperature)
                .toList();
    }

    public static List<String> extractTimes(List<WeatherRecord> records) {
        return records.stream()
                .map(WeatherRecord::time)
                .map(t -> t.format(DateTimeFormatter.ISO_DATE_TIME))
                .toList();
    }
}
