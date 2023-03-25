package org.example.model;

import java.time.LocalDate;
import java.util.Objects;

public class CarsByDate {
    private final int noOfCars;
    private final LocalDate date;

    public CarsByDate(LocalDate date, int noOfCars) {
        this.noOfCars = noOfCars;
        this.date = date;
    }

    public LocalDate getDate() {
        return date;
    }
    @Override
    public String toString() {
        return  date + " " + noOfCars;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CarsByDate that = (CarsByDate) o;
        return noOfCars == that.noOfCars && Objects.equals(date, that.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(noOfCars, date);
    }
}
