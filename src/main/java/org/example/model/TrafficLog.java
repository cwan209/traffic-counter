package org.example.model;

import java.time.LocalDateTime;

public class TrafficLog {
    private LocalDateTime timestamp;
    private int noOfCars;

    public TrafficLog(LocalDateTime timestamp, int noOfCars) {
        this.timestamp = timestamp;
        this.noOfCars = noOfCars;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public int getNoOfCars() {
        return noOfCars;
    }

    public void setNoOfCars(int noOfCars) {
        this.noOfCars = noOfCars;
    }
}
