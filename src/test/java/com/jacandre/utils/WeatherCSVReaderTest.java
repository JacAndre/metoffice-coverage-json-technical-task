package com.jacandre.utils;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Slf4j
@ExtendWith(MockitoExtension.class)
public class WeatherCSVReaderTest {
    @InjectMocks
    private WeatherCSVReader csvReader;

    @Test
    void readFromFile_shouldParseCorrectNumberOfRows_whenCSVFile_isValid() throws Exception {
        WeatherCSVReader reader = new WeatherCSVReader();
        String path = "src/test/resources/valid.csv";

        List<String[]> records = reader.readFromFile(path);
        assertEquals(3, records.size());
    }
}
