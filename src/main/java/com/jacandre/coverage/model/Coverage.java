package com.jacandre.coverage.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)public class Coverage {
    private String type;
    private Domain domain;
    private Map<String, Parameter> parameters;
    private Map<String, Range> ranges;
}
