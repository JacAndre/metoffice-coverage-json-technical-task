package com.jacandre.coverage.mapper;

import com.jacandre.coverage.model.Axes;
import com.jacandre.coverage.model.Domain;
import com.jacandre.coverage.model.Reference;
import com.jacandre.coverage.model.System;
import com.jacandre.model.WeatherRecord;

import java.util.List;

import static com.jacandre.utils.WeatherRecordMapperHelper.*;

public class DomainMapper {

    private static final String GEOGRAPHIC_CRS_ID = "http://www.opengis.net/def/crs/EPSG/0/4979";
    private static final String TEMPORAL_CALENDAR = "Gregorian";

    public Domain map(List<WeatherRecord> records) {
        Domain domain = new Domain();
        domain.setType("Domain");
        domain.setDomainType("Grid");
        domain.setAxes(mapAxis(records));
        domain.setReferencing(mapReferencing());

        return domain;
    }

    private Axes mapAxis(List<WeatherRecord> records) {
        Axes axes = new Axes();
        axes.setX(extractLatitudes(records));
        axes.setY(extractLongitudes(records));
        axes.setT(extractTimes(records));

        return axes;
    }

    private List<Reference> mapReferencing() {
        return List.of(
                new Reference(
                        List.of("x", "y", "z"),
                        new System("GeographicCRS", GEOGRAPHIC_CRS_ID, null)
                ),
                new Reference(
                        List.of("x", "y", "z"),
                        new System("TemporalRS", null, TEMPORAL_CALENDAR)
                )
        );
    }
}
