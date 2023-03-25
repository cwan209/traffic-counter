package org.example;

import org.example.model.CarsByDate;
import org.example.model.TrafficLog;

import java.security.InvalidParameterException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
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

    public List<TrafficLog> countContiguousKHalfHoursWithLeastCars(List<TrafficLog> trafficLogs, int k) {
        if (k < 1) throw new InvalidParameterException("k can not be less than 1");

        Duration timeRange = Duration.ofMinutes(30L * (k - 1));
        int leastCars = Integer.MAX_VALUE;
        int leastCarIndex = -1;

        for (int i = 0; i < trafficLogs.size() - k + 1; i++) {
            LocalDateTime start = trafficLogs.get(i).getTimestamp();
            LocalDateTime end = start.plus(timeRange);

            // see assumption section in README
            if (end.isEqual(trafficLogs.get(i + k - 1).getTimestamp())) {
                int noOfCarsWithinTimeRange = 0;
                for(int j = i; j <= i + k - 1; j++) {
                    noOfCarsWithinTimeRange += trafficLogs.get(j).getNoOfCars();
                }
                if (noOfCarsWithinTimeRange < leastCars) {
                    leastCars = noOfCarsWithinTimeRange;
                    leastCarIndex = i;
                }
            }
        }

        List<TrafficLog> logsWithLeastCars = new ArrayList<>();
        if (leastCarIndex == -1) return logsWithLeastCars;

        for (int i = leastCarIndex; i <= leastCarIndex + k - 1; i++) {
            logsWithLeastCars.add(trafficLogs.get(i));
        }
        return logsWithLeastCars;
    }
}
