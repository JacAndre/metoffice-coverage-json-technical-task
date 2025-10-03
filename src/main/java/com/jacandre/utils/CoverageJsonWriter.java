package com.jacandre.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.jacandre.coverage.model.Coverage;

public class CoverageJsonWriter {
    private static final ObjectMapper mapper = new ObjectMapper().enable(SerializationFeature.INDENT_OUTPUT);

    public static String toJson(Coverage coverage) throws Exception {
        return mapper.writeValueAsString(coverage);
    }
}
