package org.example;

import org.example.model.CarsByDate;
import org.example.model.TrafficLog;

import java.util.List;

public class Outputer {
    private final Printer printer;
    public Outputer(Printer printer) {
        this.printer = printer;
    }

    public void outputNumberOfCarsInTotal(int numberOfCarsInTotal) {
        printer.print("Here's the number of cars in total: " + numberOfCarsInTotal);
    }

    public void outputCarsByDates(List<CarsByDate> carsByDates) {
        printer.print("Here's the number of cars by dates: ");
        carsByDates.forEach( carsByDate -> printer.print(carsByDate.toString()));
    }

    public void outputTopHalfHoursWithMostCars(List<TrafficLog> topHalfHoursWithMostCars) {
        printer.print("Here's the top " + topHalfHoursWithMostCars.size() + " half hours with the most cars:");
        topHalfHoursWithMostCars.forEach( trafficLog ->  printer.print(trafficLog.toString()));
    }

    public void outputContiguousHalfHoursWithLeastCars(List<TrafficLog> contiguousHalfHoursWithLeastCars) {
        if (contiguousHalfHoursWithLeastCars.isEmpty()) printer.print("Contiguous half hours with the least cars can not be found");

        printer.print("Here's the " + contiguousHalfHoursWithLeastCars.size() + " contiguous half hours with the least cars:");
        contiguousHalfHoursWithLeastCars.forEach( trafficLog ->  printer.print(trafficLog.toString()));
    }

    public void outputError(String errorMessage) {
        printer.print("Something went wrong: " + errorMessage);
    }
}
