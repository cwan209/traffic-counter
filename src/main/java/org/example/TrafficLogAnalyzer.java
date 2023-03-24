package org.example;

import org.example.model.TimeRange;
import org.example.model.TrafficLog;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class TrafficLogAnalyzer {
    public int countCarsInTotal(List<TrafficLog> trafficLogs) {
        return trafficLogs.stream()
                .mapToInt(TrafficLog::getNoOfCars)
                .sum();
    }

    public List<CarsByDate> countCarsByDate(List<TrafficLog> trafficLogs) {
        return trafficLogs.stream()
                .collect(
                    Collectors.groupingBy(log -> log.getTimestamp().toLocalDate(),
                    Collectors.summingInt(TrafficLog::getNoOfCars))
                )
                .entrySet().stream()
                .map(entry -> new CarsByDate(entry.getKey(), entry.getValue()))
                .sorted(Comparator.comparing(CarsByDate::getDate))
                .collect(Collectors.toList());
    }

    public List<TrafficLog> countTopKHalfHoursWithMostCars(List<TrafficLog> trafficLogs, int k) {
        return null;
    }

    public TimeRange countContiguousKHalfHoursWithLeastCars(List<TrafficLog> trafficLogs, int k) {
        return null;
    }
}
