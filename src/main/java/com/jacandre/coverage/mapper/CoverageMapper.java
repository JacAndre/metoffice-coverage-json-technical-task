package com.jacandre.coverage.mapper;

import com.jacandre.coverage.model.Coverage;
import com.jacandre.model.WeatherRecord;

import java.util.List;
import java.util.Map;

public class CoverageMapper {

    private final DomainMapper domainMapper = new DomainMapper();
    private final ParameterMapper parameterMapper = new ParameterMapper();
    private final RangeMapper rangeMapper = new RangeMapper();

    public Coverage map(List<WeatherRecord> records) {
        Coverage coverage = new Coverage();
        coverage.setType("Coverage");
        coverage.setDomain(domainMapper.map(records));
        coverage.setParameters(Map.of("temperature", parameterMapper.mapTemperatureParameter()));
        coverage.setRanges(Map.of("temperature", rangeMapper.mapTemperatureRange(records)));

        return coverage;
    }
}
