# Weather CSV to CoverageJSON Converter

This Java 21 application reads weather data from a CSV file and outputs a valid CoverageJSON file representing a time series at a single geographic point.
The generated CoverageJSON file can be validated at [https://covjson.org/playground/](https://covjson.org/playground/).
Written as part of a MetOffice Technical Task.

## How to Run

### Run the application

```shell
mvn clean compile exec:java -Dexec.mainClass="com.jacandre.App"
```

This reads from `data/input/weather.csv` and writes a CoverageJSON file to `data/output/`.

### Run tests

```shell
mvn test
```

Unit tests cover CSV reading, validation, Coverage mapping, and JSON serialization.

## Project Structure

```text
metoffice-coverage-json-technical-task/
├── data/
│   ├── input/     # Contains the source CSV file(s)
│   └── output/    # Stores generated CoverageJSON files
│
├── src/
│   ├── main/
│   │   └── java/
│   │       └── com.jacandre/
│   │           ├── App.java         # Entry point for running the application
│   │           ├── coverage/
│   │           │   ├── mapper/      # Maps WeatherRecord data to CoverageJSON structure
│   │           │   └── model/       # Classes that make up CoverageJSON structure
│   │           ├── model/           # Classes used outside of Coverage
│   │           └── utils/           # Utility classes for reading CSV, validating rows, writing JSON, mapping helpers
│   │
│   └── test/
│       ├── java/
│       │   └── com.jacandre/
│       │       ├── coverage/mapper  # Unit tests for mappers
│       │       ├── fixtures         # Fixtures for unit tests
│       │       ├── model            # Tests for model behaviour (WeatherRecordTest etc.)
│       │       └── utils            # Tests for utility classes (CoverageJsonWriter, WeatherCsvReader etc.)
│       └── resources/input          # Static CSVs for unit tests
│
├── pom.xml         # Maven configuration and dependencies
└── README.md       # Project documentation
```

The application reads weather data from `data/input/` using `WeatherCsvReader`, validates it using `WeatherCsvValidator`, maps it to coverage models via the `coverage/mapper/` package starting at `CoverageMapper`, and writes a CoverageJSON file to `data/output/` using `CoverageJsonWriter` and `CoverageJsonExporter`.

## Assumptions & Design Choices

This project was written with the following assumptions in mind:
- **Temperature Constant**: Temperature will always be in Kelvin
- **Geographic Location**: The CSV provided will always be in a single location
- **Static Metadata**: Metadata labels will always be static
- **WGS 84**: Latitude and longitude will always be given in EPSG:4326 (WGS 84)
- **No Null Checks**: All null values will be handled by `@JsonInclude(JsonInclude.Include.NON_NULL)`
- **Row Validation**: Any row that does not pass structural and semantic validation will be skipped
- **Export Result**: The result should be exported to a json file

## Design Principles

- **Separation of concerns**: Reader, validator, mapper, and writer are modular and independently testable.
- **Extensibility**: The CoverageJSON model is broken into specific classes for easy extension.
- **Testability**: All core components are covered by unit tests with fixtures and isolated logic.

## Tools & Dependencies

### Core Libraries

| Library              | Purpose                                |
|----------------------|----------------------------------------|
| Jackson Databind     | Serializes Java objects to JSON format |
| OpenCSV              | Parses CSV files to Java Objects       |
| Apache Commons Lang	 | Utility functions                      |
| SLF4J                | Logging                                |
| Lombok               | Reduces boilerplate methods            |

### Testing Libraries

| Library               | Purpose                          |
|-----------------------|----------------------------------|
| JUnit Jupiter API     | Unit testing framework           |
| JUnit Jupiter Engine  | Executes JUnit 5 tests           |
| Mockito Core          | Mocking framework for unit tests |
| Mockito JUnit Jupiter | Mockito JUnit 5 integration      |
