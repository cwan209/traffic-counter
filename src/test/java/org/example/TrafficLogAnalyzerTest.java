package org.example;

import org.example.model.CarsByDate;
import org.example.model.TrafficLog;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.security.InvalidParameterException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

class TrafficLogAnalyzerTest {
    private TrafficLogAnalyzer subject;
    private List<TrafficLog> trafficLogs ;

    @BeforeEach
    public void setup() {
        subject = new TrafficLogAnalyzer();
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
    void countContiguousKHalfHoursWithLeastCars_should_find_time_range_correctly_with_no_missing_records_in_between() {
        // given
        trafficLogs = new ArrayList<>();
        trafficLogs.add(new TrafficLog(LocalDateTime.of(2021, 12, 1, 5, 0, 0), 5));
        trafficLogs.add(new TrafficLog(LocalDateTime.of(2021, 12, 1, 5, 30, 0), 12));
        trafficLogs.add(new TrafficLog(LocalDateTime.of(2021, 12, 1, 6, 0, 0), 14));
        trafficLogs.add(new TrafficLog(LocalDateTime.of(2021, 12, 1, 6, 30, 0), 15));
        trafficLogs.add(new TrafficLog(LocalDateTime.of(2021, 12, 1, 7, 0, 0), 25));
        trafficLogs.add(new TrafficLog(LocalDateTime.of(2021, 12, 1, 7, 30, 0), 8));
        trafficLogs.add(new TrafficLog(LocalDateTime.of(2021, 12, 1, 8, 0, 0), 9));

        // when
        List<TrafficLog> actual = subject.countContiguousKHalfHoursWithLeastCars(trafficLogs, 3);

        // then
        List<TrafficLog> expected = new ArrayList<>();
        expected.add(new TrafficLog(LocalDateTime.of(2021, 12, 1, 5, 0, 0), 5));
        expected.add(new TrafficLog(LocalDateTime.of(2021, 12, 1, 5, 30, 0), 12));
        expected.add(new TrafficLog(LocalDateTime.of(2021, 12, 1, 6, 0, 0), 14));

        Assertions.assertEquals(expected, actual);
    }
    @Test
    void countContiguousKHalfHoursWithLeastCars_should_discard_range_containing_missing_log() {
        // given
        trafficLogs = new ArrayList<>();
        trafficLogs.add(new TrafficLog(LocalDateTime.of(2021, 12, 1, 5, 0, 0), 5));
        trafficLogs.add(new TrafficLog(LocalDateTime.of(2021, 12, 1, 5, 30, 0), 15));
        trafficLogs.add(new TrafficLog(LocalDateTime.of(2021, 12, 1, 6, 0, 0), 2));

        trafficLogs.add(new TrafficLog(LocalDateTime.of(2021, 12, 1, 8, 30, 0), 0));

        trafficLogs.add(new TrafficLog(LocalDateTime.of(2021, 12, 1, 10, 0, 0), 9));
        trafficLogs.add(new TrafficLog(LocalDateTime.of(2021, 12, 1, 10, 30, 0), 19));

        trafficLogs.add(new TrafficLog(LocalDateTime.of(2021, 12, 1, 23, 30, 0), 0));
        trafficLogs.add(new TrafficLog(LocalDateTime.of(2021, 12, 2, 0, 0, 0), 1));
        trafficLogs.add(new TrafficLog(LocalDateTime.of(2021, 12, 2, 0, 30, 0), 2));
        trafficLogs.add(new TrafficLog(LocalDateTime.of(2021, 12, 2, 1, 0, 0), 9));

        // when
        List<TrafficLog> actual = subject.countContiguousKHalfHoursWithLeastCars(trafficLogs, 3);

        // then
        List<TrafficLog> expected = new ArrayList<>();
        expected.add(new TrafficLog(LocalDateTime.of(2021, 12, 1, 23, 30, 0), 0));
        expected.add(new TrafficLog(LocalDateTime.of(2021, 12, 2, 0, 0, 0), 1));
        expected.add(new TrafficLog(LocalDateTime.of(2021, 12, 2, 0, 30, 0), 2));

        Assertions.assertEquals(expected, actual);
    }

    @Test
    void countContiguousKHalfHoursWithLeastCars_should_return_an_empty_list_if_no_valid_range_can_be_found() {
        // given
        trafficLogs = new ArrayList<>();
        trafficLogs.add(new TrafficLog(LocalDateTime.of(2021, 12, 1, 5, 0, 0), 5));
        trafficLogs.add(new TrafficLog(LocalDateTime.of(2021, 12, 1, 5, 30, 0), 15));

        trafficLogs.add(new TrafficLog(LocalDateTime.of(2021, 12, 1, 8, 30, 0), 0));

        trafficLogs.add(new TrafficLog(LocalDateTime.of(2021, 12, 1, 10, 0, 0), 9));
        trafficLogs.add(new TrafficLog(LocalDateTime.of(2021, 12, 1, 10, 30, 0), 19));

        trafficLogs.add(new TrafficLog(LocalDateTime.of(2021, 12, 1, 23, 30, 0), 0));
        trafficLogs.add(new TrafficLog(LocalDateTime.of(2021, 12, 2, 0, 0, 0), 1));
        // when
        List<TrafficLog> actual = subject.countContiguousKHalfHoursWithLeastCars(trafficLogs, 3);

        // then
        List<TrafficLog> expected = new ArrayList<>();
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void countContiguousKHalfHoursWithLeastCars_should_return_correct_range_with_dynamic_k_value() {
        // given
        trafficLogs = new ArrayList<>();
        trafficLogs.add(new TrafficLog(LocalDateTime.of(2021, 12, 1, 5, 0, 0), 5));
        trafficLogs.add(new TrafficLog(LocalDateTime.of(2021, 12, 1, 5, 30, 0), 15));

        trafficLogs.add(new TrafficLog(LocalDateTime.of(2021, 12, 1, 8, 30, 0), 0));

        trafficLogs.add(new TrafficLog(LocalDateTime.of(2021, 12, 1, 10, 0, 0), 9));
        trafficLogs.add(new TrafficLog(LocalDateTime.of(2021, 12, 1, 10, 30, 0), 19));

        trafficLogs.add(new TrafficLog(LocalDateTime.of(2021, 12, 1, 23, 30, 0), 0));
        trafficLogs.add(new TrafficLog(LocalDateTime.of(2021, 12, 2, 0, 0, 0), 1));
        // when
        List<TrafficLog> actual = subject.countContiguousKHalfHoursWithLeastCars(trafficLogs, 2);

        // then
        List<TrafficLog> expected = new ArrayList<>();
        expected.add(new TrafficLog(LocalDateTime.of(2021, 12, 1, 23, 30, 0), 0));
        expected.add(new TrafficLog(LocalDateTime.of(2021, 12, 2, 0, 0, 0), 1));
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void countContiguousKHalfHoursWithLeastCars_should_throw_error_if_K_is_less_than_1() {
        // then
        Assertions.assertThrows(InvalidParameterException.class,
                // when
                () -> subject.countContiguousKHalfHoursWithLeastCars(trafficLogs, 0)
        );
    }
}
