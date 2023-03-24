package org.example;

import org.example.model.TimeRange;
import org.example.model.TrafficLog;

import java.util.List;

public class TrafficCounter {
    private final TrafficLogParser trafficLogParser;
    private final TrafficLogAnalyzer trafficLogAnalyzer;
    private final Outputer outputer;
    public TrafficCounter(TrafficLogParser trafficLogParser, TrafficLogAnalyzer trafficLogAnalyzer, Outputer outputer) {
        this.trafficLogParser = trafficLogParser;
        this.trafficLogAnalyzer = trafficLogAnalyzer;
        this.outputer = outputer;
    }

    public void run(String inputFilePath) {
        List<TrafficLog> trafficLogs = trafficLogParser.parse(inputFilePath);

        int numberOfCarsInTotal = trafficLogAnalyzer.countCarsInTotal(trafficLogs);
        List<CarsByDate> carsByDates = trafficLogAnalyzer.countCarsByDate(trafficLogs);
        List<TrafficLog> topThreeHalfHoursWithMostCars = trafficLogAnalyzer.countTopKHalfHoursWithMostCars(trafficLogs, 3);
        TimeRange contiguousThreeHalfHoursWithLeastCars = trafficLogAnalyzer.countContiguousKHalfHoursWithLeastCars(trafficLogs, 3);

        outputer.outputNumberOfCarsInTotal(numberOfCarsInTotal);
        outputer.outputCarsByDates(carsByDates);
        outputer.outputTopThreeHalfHoursWithMostCars(topThreeHalfHoursWithMostCars);
        outputer.outputContiguousHalfHoursWithLeastCars(contiguousThreeHalfHoursWithLeastCars);
    }
}
