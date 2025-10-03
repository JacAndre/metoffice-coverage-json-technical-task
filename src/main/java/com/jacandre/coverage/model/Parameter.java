package com.jacandre.coverage.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Parameter {
    private String type;
    private Map<String, String> description;
    private Unit unit;
    private ObservedProperty observedProperty;
}
