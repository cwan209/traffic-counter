package org.example;

import org.example.model.TimeRange;
import org.example.model.TrafficLog;

import java.security.InvalidParameterException;
import java.util.Comparator;
import java.util.List;
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
        if (k < 1) throw new InvalidParameterException("k can not be less than 1");
        return trafficLogs.stream()
                .sorted(Comparator.comparing(TrafficLog::getNoOfCars).reversed())
                .limit(k)
                .collect(Collectors.toList());
    }

    public TimeRange countContiguousKHalfHoursWithLeastCars(List<TrafficLog> trafficLogs, int k) {
        return null;
    }
}
