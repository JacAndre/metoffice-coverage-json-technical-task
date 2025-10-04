package com.jacandre.utils;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class WeatherCSVReaderTest {

    private final WeatherCSVReader reader = new WeatherCSVReader();

    @Test
    void readFromFile_shouldReturnDataRows_andSkipHeader_whenCSVIsValid() {
        String path = "src/test/resources/input/valid.csv";
        List<String[]> records = reader.readFromFile(path);

        assertNotNull(records);
        assertEquals(3, records.size());
        assertEquals("275.2", records.getFirst()[3]);
        assertEquals(3, records.size());
        assertEquals("2025-10-01T02:00Z", records.get(2)[0]);
    }

    @Test
    void readFromFile_shouldReturnEmptyList_whenFileDoesNotExist() {
        String path = "src/test/resources/input/missing.csv";
        List<String[]> records = reader.readFromFile(path);

        assertTrue(records.isEmpty());
    }

    @Test
    void readFromFile_shouldReturnEmptyList_whenFileHasOnlyHeader() {
        String path = "src/test/resources/input/empty.csv";
        List<String[]> records = reader.readFromFile(path);

        assertTrue(records.isEmpty());
    }
}
