package com.jacandre.coverage.mapper;

import com.jacandre.coverage.model.Range;
import com.jacandre.model.WeatherRecord;

import java.util.List;

import static com.jacandre.utils.WeatherRecordMapperHelper.extractTemperatures;

public class RangeMapper {

    public Range mapTemperatureRange(List<WeatherRecord> records) {
        Range range = new Range();
        List<Double> temperatures = extractTemperatures(records);
        range.setType("NdArray");
        range.setDataType("float");
        range.setAxisNames(List.of("t", "y", "x"));
        range.setShape(List.of(temperatures.size(), 1, 1));
        range.setValues(temperatures);

        return range;
    }
}
