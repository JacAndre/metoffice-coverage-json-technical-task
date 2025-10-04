package com.jacandre.utils;

import com.jacandre.coverage.model.Coverage;
import lombok.extern.slf4j.Slf4j;

import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Slf4j
public class CoverageJsonExporter {

    public void exportToFile(Coverage coverage) {
        try {
            LocalDateTime now = LocalDateTime.now();
            String timeStamp = now.format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss"));

            Path outputDir = Path.of("data/output");
            Files.createDirectories(outputDir);

            Path outputFile = outputDir.resolve("coverage_" + timeStamp + ".json");
            Files.writeString(outputFile, CoverageJsonWriter.toJson(coverage));

            log.info("CoverageJSON written to {}", outputFile.toAbsolutePath());
        } catch (Exception e) {
            log.error("CoverageJsonExporter::exportToFile - Failed to export CoverageJSON", e);
        }
    }
}
