package com.jacandre.utils;

import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.exceptions.CsvException;
import lombok.extern.slf4j.Slf4j;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

@Slf4j
public class WeatherCSVReader {

    public List<String[]> readFromFile(String fileName) {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName));
             CSVReader csvReader = new CSVReaderBuilder(reader)
                     .withSkipLines(1)
                     .build()) {

            return csvReader.readAll();
        } catch (IOException e) {
            log.error("WeatherCSVReader::readFromFile - I/O error reading file '{}': {}", fileName, e.getMessage(), e);
        } catch (CsvException e) {
            log.error("WeatherCSVReader::readFromFile - CSV parsing error in file '{}': {}", fileName, e.getMessage(), e);
        }

        return List.of();
    }
}
