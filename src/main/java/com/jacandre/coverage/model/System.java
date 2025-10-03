package com.jacandre.coverage.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class System {
    private String type;
    private String id;          // Optional for Geographic
    private String calendar;    // Optional for Temporal
}
