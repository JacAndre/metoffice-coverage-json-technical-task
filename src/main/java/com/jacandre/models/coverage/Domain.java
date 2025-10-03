package com.jacandre.models.coverage;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)public class Domain {
    private String type;
    private String domainType;
    private Axes axes;
    private List<Reference> referencing;
}
