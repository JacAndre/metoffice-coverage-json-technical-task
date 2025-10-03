package com.jacandre;

import com.jacandre.coverage.mapper.CoverageMapper;
import com.jacandre.model.WeatherRecord;
import com.jacandre.utils.WeatherCSVReader;
import com.jacandre.utils.WeatherCSVValidator;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

import static com.jacandre.utils.CoverageJsonWriter.toJson;

@Slf4j
public class App 
{
    public static void main( String[] args ) throws Exception {
        WeatherCSVReader reader = new WeatherCSVReader();
        WeatherCSVValidator validator = new WeatherCSVValidator();
        List<WeatherRecord> records = new ArrayList<>();

        var parsedCsv = reader.readFromFile("data/weather.csv");
        for (String[] row : parsedCsv) {
            if (!validator.validateParsedRow(row)) return;
            records.add(WeatherRecord.parse(row));
        }

        CoverageMapper mapper = new CoverageMapper();
        var coverage = mapper.map(records);

        log.info("{}", toJson(coverage));
    }
}
