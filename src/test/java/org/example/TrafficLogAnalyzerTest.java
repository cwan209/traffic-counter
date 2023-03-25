package org.example;

import org.example.model.TrafficLog;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.security.InvalidParameterException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TrafficLogAnalyzerTest {
    private TrafficLogAnalyzer subject;
    private List<TrafficLog> trafficLogs ;

    @BeforeEach
    public void setup() {
        subject = new TrafficLogAnalyzer();
        trafficLogs = new ArrayList<>();
        trafficLogs.add(new TrafficLog(LocalDateTime.of(2021, 12, 1, 5, 0, 0), 5));
        trafficLogs.add(new TrafficLog(LocalDateTime.of(2021, 12, 1, 5, 30, 0), 12));
        trafficLogs.add(new TrafficLog(LocalDateTime.of(2021, 12, 1, 6, 0, 0), 14));
        trafficLogs.add(new TrafficLog(LocalDateTime.of(2021, 12, 1, 6, 30, 0), 15));
        trafficLogs.add(new TrafficLog(LocalDateTime.of(2021, 12, 1, 7, 0, 0), 25));
        trafficLogs.add(new TrafficLog(LocalDateTime.of(2021, 12, 1, 7, 30, 0), 46));
        trafficLogs.add(new TrafficLog(LocalDateTime.of(2021, 12, 1, 8, 0, 0), 42));
        trafficLogs.add(new TrafficLog(LocalDateTime.of(2021, 12, 1, 15, 0, 0), 9));
        trafficLogs.add(new TrafficLog(LocalDateTime.of(2021, 12, 1, 15, 30, 0), 11));
        trafficLogs.add(new TrafficLog(LocalDateTime.of(2021, 12, 1, 23, 30, 0), 0));
        trafficLogs.add(new TrafficLog(LocalDateTime.of(2021, 12, 5, 9, 30, 0), 18));
        trafficLogs.add(new TrafficLog(LocalDateTime.of(2021, 12, 5, 10, 30, 0), 15));
        trafficLogs.add(new TrafficLog(LocalDateTime.of(2021, 12, 5, 11, 30, 0), 7));
        trafficLogs.add(new TrafficLog(LocalDateTime.of(2021, 12, 5, 12, 30, 0), 6));
        trafficLogs.add(new TrafficLog(LocalDateTime.of(2021, 12, 5, 13, 30, 0), 9));
        trafficLogs.add(new TrafficLog(LocalDateTime.of(2021, 12, 5, 14, 30, 0), 11));
        trafficLogs.add(new TrafficLog(LocalDateTime.of(2021, 12, 5, 15, 30, 0), 15));
        trafficLogs.add(new TrafficLog(LocalDateTime.of(2021, 12, 8, 18, 0, 0), 33));
        trafficLogs.add(new TrafficLog(LocalDateTime.of(2021, 12, 8, 19, 0, 0), 28));
        trafficLogs.add(new TrafficLog(LocalDateTime.of(2021, 12, 8, 20, 0, 0), 25));
        trafficLogs.add(new TrafficLog(LocalDateTime.of(2021, 12, 8, 21, 0, 0), 21));
        trafficLogs.add(new TrafficLog(LocalDateTime.of(2021, 12, 8, 22, 0, 0), 16));
        trafficLogs.add(new TrafficLog(LocalDateTime.of(2021, 12, 8, 23, 0, 0), 11));
        trafficLogs.add(new TrafficLog(LocalDateTime.of(2021, 12, 9, 0, 0, 0), 4));
    }

    @Test
    void countCarsInTotal() {
        // given
        trafficLogs = new ArrayList<>();
        trafficLogs.add(new TrafficLog(LocalDateTime.of(2021, 12, 1, 5, 0, 0), 5));
        trafficLogs.add(new TrafficLog(LocalDateTime.of(2021, 12, 1, 5, 30, 0), 12));
        trafficLogs.add(new TrafficLog(LocalDateTime.of(2021, 12, 1, 6, 0, 0), 14));
        trafficLogs.add(new TrafficLog(LocalDateTime.of(2021, 12, 1, 6, 30, 0), 15));

        // when
        int actual = subject.countCarsInTotal(trafficLogs);

        // then
        Assertions.assertEquals(46, actual);
    }

    @Test
    void countCarsByDate() {
        // given
        trafficLogs = new ArrayList<>();
        trafficLogs.add(new TrafficLog(LocalDateTime.of(2021, 12, 1, 5, 0, 0), 5));
        trafficLogs.add(new TrafficLog(LocalDateTime.of(2021, 12, 1, 5, 30, 0), 12));
        trafficLogs.add(new TrafficLog(LocalDateTime.of(2021, 12, 1, 6, 0, 0), 14));
        trafficLogs.add(new TrafficLog(LocalDateTime.of(2021, 12, 2, 6, 30, 0), 25));
        trafficLogs.add(new TrafficLog(LocalDateTime.of(2021, 12, 2, 7, 30, 0), 10));

        // when
        List<CarsByDate> actual = subject.countCarsByDate(trafficLogs);

        // then
        List<CarsByDate> expected = new ArrayList<>();
        expected.add(new CarsByDate(LocalDate.of(2021, 12, 1), 31));
        expected.add(new CarsByDate(LocalDate.of(2021, 12, 2), 35));
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void countTopKHalfHoursWithMostCars() {
        // given
        trafficLogs = new ArrayList<>();
        trafficLogs.add(new TrafficLog(LocalDateTime.of(2021, 12, 1, 5, 0, 0), 5));
        trafficLogs.add(new TrafficLog(LocalDateTime.of(2021, 12, 1, 5, 30, 0), 12));
        trafficLogs.add(new TrafficLog(LocalDateTime.of(2021, 12, 1, 6, 0, 0), 14));
        trafficLogs.add(new TrafficLog(LocalDateTime.of(2021, 12, 1, 6, 30, 0), 15));
        trafficLogs.add(new TrafficLog(LocalDateTime.of(2021, 12, 1, 7, 0, 0), 25));
        trafficLogs.add(new TrafficLog(LocalDateTime.of(2021, 12, 1, 7, 30, 0), 46));
        trafficLogs.add(new TrafficLog(LocalDateTime.of(2021, 12, 1, 8, 0, 0), 42));
        trafficLogs.add(new TrafficLog(LocalDateTime.of(2021, 12, 5, 9, 30, 0), 18));

        // when
        List<TrafficLog> actual = subject.countTopKHalfHoursWithMostCars(trafficLogs, 3);

        // then
        List<TrafficLog> expected = new ArrayList<>();
        expected.add(new TrafficLog(LocalDateTime.of(2021, 12, 1, 7, 30, 0), 46));
        expected.add(new TrafficLog(LocalDateTime.of(2021, 12, 1, 8, 0, 0), 42));
        expected.add(new TrafficLog(LocalDateTime.of(2021, 12, 1, 7, 0, 0), 25));
        Assertions.assertEquals(expected, actual);
    }
    @Test

    void countTopKHalfHoursWithMostCars_should_throw_error_if_K_is_less_than_1() {
        // then
        Assertions.assertThrows(InvalidParameterException.class,
                // when
                () -> subject.countTopKHalfHoursWithMostCars(trafficLogs, 0)
        );
    }

    @Test
    void countContiguousKHalfHoursWithLeastCars() {
    }
}
