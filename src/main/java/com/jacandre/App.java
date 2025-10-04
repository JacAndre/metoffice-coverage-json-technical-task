package com.jacandre;

import com.jacandre.coverage.mapper.CoverageMapper;
import com.jacandre.model.WeatherRecord;
import com.jacandre.utils.CoverageJsonExporter;
import com.jacandre.utils.WeatherCsvReader;
import com.jacandre.utils.WeatherCsvValidator;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

@Slf4j
public class App 
{
    public static void main( String[] args ) {
        WeatherCsvReader reader = new WeatherCsvReader();
        WeatherCsvValidator validator = new WeatherCsvValidator();
        List<WeatherRecord> records = new ArrayList<>();

        var parsedCsv = reader.readFromFile("data/input/weather.csv");
        for (String[] row : parsedCsv) {
            if (!validator.validateParsedRow(row)) {
                log.warn("Skipping invalid row: {}", (Object) row);
                continue;
            }
            records.add(WeatherRecord.parse(row));
        }

        CoverageMapper mapper = new CoverageMapper();
        var coverage = mapper.map(records);

        CoverageJsonExporter exporter = new CoverageJsonExporter();
        exporter.exportToFile(coverage);
    }
}
