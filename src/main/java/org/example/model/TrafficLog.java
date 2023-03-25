package org.example.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class TrafficLog {
    private final LocalDateTime timestamp;
    private final int noOfCars;

    public TrafficLog(LocalDateTime timestamp, int noOfCars) {
        this.timestamp = timestamp;
        this.noOfCars = noOfCars;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public int getNoOfCars() {
        return noOfCars;
    }

    @Override
    public String toString() {
        return timestamp.format(DateTimeFormatter.ISO_DATE_TIME) + " " + noOfCars;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TrafficLog that = (TrafficLog) o;
        return noOfCars == that.noOfCars && Objects.equals(timestamp, that.timestamp);
    }

    @Override
    public int hashCode() {
        return Objects.hash(timestamp, noOfCars);
    }
}
