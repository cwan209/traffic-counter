package org.example;

import java.time.LocalDate;
import java.util.Objects;

public class CarsByDate {
    private int noOfCars;
    private LocalDate date;

    public CarsByDate(LocalDate date, int noOfCars) {
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

    @Override
    public String toString() {
        return "CarsByDate{" +
                "noOfCars=" + noOfCars +
                ", date=" + date +
                '}';
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
