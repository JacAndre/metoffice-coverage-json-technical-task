package com.jacandre.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.jacandre.coverage.model.Coverage;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CoverageJsonWriter {
    private static final ObjectMapper mapper = new ObjectMapper().enable(SerializationFeature.INDENT_OUTPUT);

    public static String toJson(Coverage coverage) {
        try {
            return mapper.writeValueAsString(coverage);
        } catch (JsonProcessingException e) {
            log.error("CoverageJsonWriter::toJson - Failed to serialise Coverage", e);
            return "{}";
        }
    }
}
