package com.jacandre.models.coverage;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Range {
    private String type;
    private String dataType;
    private List<String> axisNames;
    private List<Integer> shape;
    private List<Double> values;
}
