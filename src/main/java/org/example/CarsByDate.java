package org.example;

import java.time.LocalDate;

public class CarsByDate {
    private int noOfCars;
    private LocalDate date;

    public CarsByDate(int noOfCars, LocalDate date) {
        this.noOfCars = noOfCars;
        this.date = date;
    }

    public int getNoOfCars() {
        return noOfCars;
    }

    public void setNoOfCars(int noOfCars) {
        this.noOfCars = noOfCars;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}
