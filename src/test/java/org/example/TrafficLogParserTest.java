package org.example;

import org.example.model.TrafficLog;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

class TrafficLogParserTest {
    private TrafficLogParser trafficLogParser;
    private static final String INPUT_FILE_PATH = "test_input_file";

    @BeforeEach
    public void setup() {
        trafficLogParser = new TrafficLogParser();
    }

    @Test
    void should_parse_file_to_traffic_logs_correctly() throws FileNotFoundException {
        // when
        List<TrafficLog> actual = trafficLogParser.parse(INPUT_FILE_PATH);

        // then
        List<TrafficLog> expected = new ArrayList<>();
        expected.add(new TrafficLog(LocalDateTime.of(2021, 12, 1, 5, 0, 0), 5));
        expected.add(new TrafficLog(LocalDateTime.of(2021, 12, 1, 5, 30, 0), 12));
        expected.add(new TrafficLog(LocalDateTime.of(2021, 12, 1, 6, 0, 0), 14));

        Assertions.assertEquals(expected, actual);
    }
    @Test
    void should_throw_error_when_the_file_does_not_exist() {
        // then
        Assertions.assertThrows(FileNotFoundException.class,
                // when
                () -> trafficLogParser.parse("not_exist_file")
        );
    }
    @Test
    void should_throw_error_when_the_file_is_empty() {
        // then
        Assertions.assertThrows(NoTrafficLogException.class,
                // when
                () -> trafficLogParser.parse("test_empty_file")
        );
    }
}
