package com.jacandre.fixtures;

import com.jacandre.coverage.model.*;
import com.jacandre.coverage.model.System;

import java.util.List;
import java.util.Map;

public class CoverageFixtures {

    public static Coverage validCoverage() {
        Coverage coverage = new Coverage();
        coverage.setType("Coverage");

        Domain domain = getDomain();
        coverage.setDomain(domain);

        Parameter temperature = getParameter();
        coverage.setParameters(Map.of("temperature", temperature));

        Range range = getRange();
        coverage.setRanges(Map.of("temperature", range));

        return coverage;
    }

    private static Domain getDomain() {
        Domain domain = new Domain();
        domain.setType("Domain");
        domain.setDomainType("Grid");
        domain.setAxes(new Axes(
                List.of(-3.48),
                List.of(50.73),
                List.of(2.0),
                List.of("2025-10-01T00:00Z")
        ));
        domain.setReferencing(List.of(
                new Reference(List.of("x", "y", "z"), new System("GeographicCRS", "http://www.opengis.net/def/crs/EPSG/0/4979", null)),
                new Reference(List.of("t"), new System("TemporalRS", null, "Gregorian"))
        ));
        return domain;
    }

    private static Parameter getParameter() {
        Parameter parameter = new Parameter();
        parameter.setType("Parameter");
        parameter.setObservedProperty(new ObservedProperty("Air temperature", Map.of("en", "http://vocab.nerc.ac.uk/standard_name/air_temperature/")));
        parameter.setUnit(new Unit(Map.of("en", "Kelvin"), new Symbol("K", "https://ucum.org/ucum#para-28")));
        parameter.setDescription(Map.of("en", "Air temperature"));
        return parameter;
    }

    private static Range getRange() {
        Range range = new Range();
        range.setType("NdArray");
        range.setDataType("float");
        range.setAxisNames(List.of("t"));
        range.setShape(List.of(1));
        range.setValues(List.of(275.2));
        return range;
    }

    public static Coverage invalidCoverage() {
        Coverage coverage = new Coverage();
        coverage.setType(null);
        coverage.setDomain(null);
        coverage.setParameters(null);
        coverage.setRanges(null);

        return coverage;
    }
}
