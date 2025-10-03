package com.jacandre.coverage.mapper;

import com.jacandre.coverage.model.ObservedProperty;
import com.jacandre.coverage.model.Parameter;
import com.jacandre.coverage.model.Symbol;
import com.jacandre.coverage.model.Unit;

import java.util.Map;

public class ParameterMapper {

    private static final String TEMPERATURE_UNIT_TYPE_UCUM = "http://www.opengis.net/def/uom/UCUM/";
    private static final String TEMPERATURE_OBSERVED_PROPERTY_ID = "http://vocab.nerc.ac.uk/standard_name/air_temperature/";

    public Parameter mapParameter() {
        Parameter parameter = new Parameter();
        parameter.setType("Parameter");
        parameter.setDescription(Map.of("en", "Air temperature"));

        Unit unit = new Unit();
        unit.setLabel(Map.of("en", "Kelvin"));
        unit.setSymbol(new Symbol("K", TEMPERATURE_UNIT_TYPE_UCUM));
        parameter.setUnit(unit);

        ObservedProperty observedProperty = new ObservedProperty();
        observedProperty.setId(TEMPERATURE_OBSERVED_PROPERTY_ID);
        observedProperty.setLabel(Map.of("en", "Air temperature"));
        parameter.setObservedProperty(observedProperty);

        return parameter;
    }
}
