package org.example;

import org.example.model.TrafficLog;

import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TrafficLogParser {
    private final DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
    public List<TrafficLog> parse(String inputFilePath) throws FileNotFoundException {
        List<TrafficLog> trafficLogs = new ArrayList<>();
        try (Scanner scanner = new Scanner(new File(inputFilePath))) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                trafficLogs.add(parseTrafficLog(line));
            }
        }
        return trafficLogs;
    }

    private TrafficLog parseTrafficLog(String line) {
        String[] lineParts = line.split(" ");
        LocalDateTime timestamp = LocalDateTime.parse(lineParts[0], formatter);
        int noOfCars = Integer.parseInt(lineParts[1]);
        return new TrafficLog(timestamp, noOfCars);
    }
}
