package com.jacandre.utils;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jacandre.coverage.model.Coverage;
import com.jacandre.fixtures.CoverageFixtures;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CoverageJsonWriterTest {

    @Test
    void toJson_shouldProduceCorrectStructure_whenCoverageIsValid() throws Exception {
        Coverage coverage = CoverageFixtures.validCoverage();
        String json = CoverageJsonWriter.toJson(coverage);

        ObjectMapper mapper = new ObjectMapper();
        JsonNode root = mapper.readTree(json);

        // Top-level
        assertEquals("Coverage", root.get("type").asText());

        // Domain
        JsonNode domain = root.get("domain");
        assertEquals("Domain", domain.get("type").asText());
        assertEquals("Grid", domain.get("domainType").asText());

        JsonNode axes = domain.get("axes");
        assertEquals(-3.48, axes.get("x").get(0).asDouble());
        assertEquals(50.73, axes.get("y").get(0).asDouble());
        assertEquals(2.0, axes.get("z").get(0).asDouble());
        assertEquals("2025-10-01T00:00Z", axes.get("t").get(0).asText());

        JsonNode referencing = domain.get("referencing");
        JsonNode geoRef = referencing.get(0);
        assertEquals("GeographicCRS", geoRef.get("system").get("type").asText());
        assertEquals("http://www.opengis.net/def/crs/EPSG/0/4979", geoRef.get("system").get("id").asText());
        assertTrue(geoRef.get("coordinates").toString().contains("x"));
        assertTrue(geoRef.get("coordinates").toString().contains("y"));
        assertTrue(geoRef.get("coordinates").toString().contains("z"));

        JsonNode timeRef = referencing.get(1);
        assertEquals("TemporalRS", timeRef.get("system").get("type").asText());
        assertEquals("Gregorian", timeRef.get("system").get("calendar").asText());
        assertEquals("t", timeRef.get("coordinates").get(0).asText());

        // Parameters
        JsonNode temperature = root.get("parameters").get("temperature");
        assertEquals("Parameter", temperature.get("type").asText());
        assertEquals("Air temperature", temperature.get("description").get("en").asText());
        assertEquals("Kelvin", temperature.get("unit").get("label").get("en").asText());
        assertEquals("K", temperature.get("unit").get("symbol").get("value").asText());
        assertEquals("https://ucum.org/ucum#para-28", temperature.get("unit").get("symbol").get("type").asText());
        assertEquals("Air temperature", temperature.get("observedProperty").get("id").asText());
        assertEquals("http://vocab.nerc.ac.uk/standard_name/air_temperature/", temperature.get("observedProperty").get("label").get("en").asText());

        // Ranges
        JsonNode range = root.get("ranges").get("temperature");
        assertEquals("NdArray", range.get("type").asText());
        assertEquals("float", range.get("dataType").asText());
        assertEquals("t", range.get("axisNames").get(0).asText());
        assertEquals(1, range.get("shape").get(0).asInt());
        assertEquals(275.2, range.get("values").get(0).asDouble());
    }

    @Test
    void toJson_shouldReturnEmptyJson_whenCoverageIsUnserializable() {
        Coverage malformed = CoverageFixtures.invalidCoverage();
        String json = CoverageJsonWriter.toJson(malformed);

        assertNotNull(json);
        assertEquals("{}", removeWhitespace(json));
    }

    private String removeWhitespace(String string) {
        return string.replaceAll("\\s", "");
    }
}
