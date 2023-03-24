package org.example;

import org.example.model.TimeRange;
import org.example.model.TrafficLog;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@ExtendWith(MockitoExtension.class)
class TrafficCounterTest {
    @InjectMocks
    private TrafficCounter subject;
    @Mock
    private TrafficLogParser trafficLogParser;
    @Mock
    private TrafficLogAnalyzer trafficLogAnalyzer;
    @Mock
    private Outputer outputer;

    private static final String INPUT_FILE_PATH = "input_file";

    @BeforeEach
    public void setup() {
        subject = new TrafficCounter(trafficLogParser, trafficLogAnalyzer, outputer);
    }

    @Test
    void should_parse_input_file_and_analyze_logs_and_output_result() {
        // given
        LocalDateTime now = LocalDateTime.now();
        int numberOfCarsInTotal = 5;

        List<TrafficLog> trafficLogs = new ArrayList<>();
        trafficLogs.add(new TrafficLog(now, 1));

        List<CarsByDate> carsByDates = new ArrayList<>();
        carsByDates.add(new CarsByDate(2, LocalDate.now()));

        List<TrafficLog> topThreeHalfHoursWithMostCars = new ArrayList<>();
        topThreeHalfHoursWithMostCars.add(new TrafficLog(now.plus(Duration.ofMinutes(90)), 3));
        topThreeHalfHoursWithMostCars.add(new TrafficLog(now.plus(Duration.ofMinutes(30)), 5));
        topThreeHalfHoursWithMostCars.add(new TrafficLog(now.plus(Duration.ofMinutes(60)), 4));

        TimeRange contiguousHalfHoursWithLeastCars = new TimeRange(now, now.plus(Duration.ofMinutes(90)));

        Mockito.when(trafficLogParser.parse(INPUT_FILE_PATH)).thenReturn(trafficLogs);

        Mockito.when(trafficLogAnalyzer.countCarsInTotal(trafficLogs)).thenReturn(numberOfCarsInTotal);
        Mockito.when(trafficLogAnalyzer.countCarsByDate(trafficLogs)).thenReturn(carsByDates);
        Mockito.when(trafficLogAnalyzer.countTopKHalfHoursWithMostCars(trafficLogs, 3)).thenReturn(topThreeHalfHoursWithMostCars);
        Mockito.when(trafficLogAnalyzer.countContiguousKHalfHoursWithLeastCars(trafficLogs, 3)).thenReturn(contiguousHalfHoursWithLeastCars);

        Mockito.doNothing().when(outputer).outputNumberOfCarsInTotal(numberOfCarsInTotal);
        Mockito.doNothing().when(outputer).outputCarsByDates(carsByDates);
        Mockito.doNothing().when(outputer).outputTopThreeHalfHoursWithMostCars(topThreeHalfHoursWithMostCars);
        Mockito.doNothing().when(outputer).outputContiguousHalfHoursWithLeastCars(contiguousHalfHoursWithLeastCars);

        // when
        subject.run(INPUT_FILE_PATH);

        // then
        Mockito.verify(trafficLogParser).parse(INPUT_FILE_PATH);

        Mockito.verify(trafficLogAnalyzer).countCarsInTotal(trafficLogs);
        Mockito.verify(trafficLogAnalyzer).countCarsByDate(trafficLogs);
        Mockito.verify(trafficLogAnalyzer).countTopKHalfHoursWithMostCars(trafficLogs, 3);
        Mockito.verify(trafficLogAnalyzer).countContiguousKHalfHoursWithLeastCars(trafficLogs, 3);

        Mockito.verify(outputer).outputNumberOfCarsInTotal(numberOfCarsInTotal);
        Mockito.verify(outputer).outputCarsByDates(carsByDates);
        Mockito.verify(outputer).outputTopThreeHalfHoursWithMostCars(topThreeHalfHoursWithMostCars);
        Mockito.verify(outputer).outputContiguousHalfHoursWithLeastCars(contiguousHalfHoursWithLeastCars);
    }
}
