package org.example;

import org.example.model.CarsByDate;
import org.example.model.TrafficLog;

import java.util.List;

public class TrafficCounter {
    private final TrafficLogParser trafficLogParser;
    private final TrafficLogAnalyzer trafficLogAnalyzer;
    private final ArgsParser argsParser;
    private final Outputer outputer;
    public TrafficCounter(TrafficLogParser trafficLogParser, TrafficLogAnalyzer trafficLogAnalyzer, Outputer outputer, ArgsParser argsParser) {
        this.trafficLogParser = trafficLogParser;
        this.trafficLogAnalyzer = trafficLogAnalyzer;
        this.outputer = outputer;
        this.argsParser = argsParser;
    }

    public void run(String[] args) {
        try {
            String inputFilePath = argsParser.parseInputFilePath(args);
            List<TrafficLog> trafficLogs = trafficLogParser.parse(inputFilePath);

            int numberOfCarsInTotal = trafficLogAnalyzer.countCarsInTotal(trafficLogs);
            outputer.outputNumberOfCarsInTotal(numberOfCarsInTotal);

            List<CarsByDate> carsByDates = trafficLogAnalyzer.countCarsByDate(trafficLogs);
            outputer.outputCarsByDates(carsByDates);

            List<TrafficLog> topThreeHalfHoursWithMostCars = trafficLogAnalyzer.countTopKHalfHoursWithMostCars(trafficLogs, 3);
            outputer.outputTopHalfHoursWithMostCars(topThreeHalfHoursWithMostCars);

            List<TrafficLog> contiguousThreeHalfHoursWithLeastCars = trafficLogAnalyzer.countContiguousKHalfHoursWithLeastCars(trafficLogs, 3);
            outputer.outputContiguousHalfHoursWithLeastCars(contiguousThreeHalfHoursWithLeastCars);
        } catch (Exception e) {
            outputer.outputError(e.getMessage());
        }
    }
}
