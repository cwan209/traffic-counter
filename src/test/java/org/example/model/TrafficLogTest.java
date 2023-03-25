package org.example.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class TrafficLogTest {

    @Test
    void testToString() {
        // given
        TrafficLog trafficLog = new TrafficLog(LocalDateTime.of(2021, 12, 1, 5, 4, 3), 5);

        // when
        String actual = trafficLog.toString();

        // then
        String expected = "2021-12-01T05:04:03 5";
        Assertions.assertEquals(expected, actual);
    }
}
