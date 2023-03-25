package org.example;

import org.example.model.TrafficLog;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@ExtendWith(MockitoExtension.class)
class OutputerTest {

    @InjectMocks
    private Outputer subject;

    @Mock
    private Printer printer;

    @BeforeEach
    public void setUp() {
        subject = new Outputer(printer);
    }

    @Test
    void outputNumberOfCarsInTotal() {
        // given
        int numberOfCarsInTotal = 88;

        // when
        subject.outputNumberOfCarsInTotal(numberOfCarsInTotal);

        // then
        Mockito.verify(printer).print("Here's the number of cars in total: 88");
    }

    @Test
    void outputCarsByDates() {
        // given
        List<CarsByDate> carsByDateList = new ArrayList<>();
        carsByDateList.add(new CarsByDate(LocalDate.of(2021, 10, 10), 12));
        carsByDateList.add(new CarsByDate(LocalDate.of(2022, 1, 12), 20));

        // when
        subject.outputCarsByDates(carsByDateList);

        // then
        Mockito.verify(printer).print("Here's the number of cars by dates: ");
        Mockito.verify(printer).print("2021-10-10 12");
        Mockito.verify(printer).print("2022-01-12 20");
    }

    @Test
    void outputTopHalfHoursWithMostCars() {
        // given
        List<TrafficLog> trafficLogs = new ArrayList<>();
        trafficLogs.add(new TrafficLog(LocalDateTime.of(2021, 12, 1, 5, 0, 0), 5));
        trafficLogs.add(new TrafficLog(LocalDateTime.of(2021, 12, 1, 5, 30, 0), 12));
        trafficLogs.add(new TrafficLog(LocalDateTime.of(2021, 12, 1, 6, 0, 0), 14));

        // when
        subject.outputTopHalfHoursWithMostCars(trafficLogs);

        // then
        Mockito.verify(printer).print("Here's the top 3 half hours with the most cars:");
        Mockito.verify(printer).print("2021-12-01T05:00:00 5");
        Mockito.verify(printer).print("2021-12-01T05:30:00 12");
        Mockito.verify(printer).print("2021-12-01T06:00:00 14");
    }

    @Test
    void outputContiguousHalfHoursWithLeastCars() {
        // given
        List<TrafficLog> trafficLogs = new ArrayList<>();
        trafficLogs.add(new TrafficLog(LocalDateTime.of(2021, 12, 1, 5, 0, 0), 5));
        trafficLogs.add(new TrafficLog(LocalDateTime.of(2021, 12, 1, 5, 30, 0), 12));
        trafficLogs.add(new TrafficLog(LocalDateTime.of(2021, 12, 1, 6, 0, 0), 14));

        // when
        subject.outputContiguousHalfHoursWithLeastCars(trafficLogs);

        // then
        Mockito.verify(printer).print("Here's the 3 contiguous half hours with the least cars:");
        Mockito.verify(printer).print("2021-12-01T05:00:00 5");
        Mockito.verify(printer).print("2021-12-01T05:30:00 12");
        Mockito.verify(printer).print("2021-12-01T06:00:00 14");
    }

    @Test
    void outputContiguousHalfHoursWithLeastCars_should_output_error_message_if_there_is_no_valid_contiguous_half_hours() {
        // given
        List<TrafficLog> trafficLogs = new ArrayList<>();

        // when
        subject.outputContiguousHalfHoursWithLeastCars(trafficLogs);

        // then
        Mockito.verify(printer).print("Contiguous half hours with the least cars can not be found");
    }

    @Test
    void outputError() {
        // given
        String errorMessage = "file is empty";

        // when
        subject.outputError(errorMessage);

        // then
        Mockito.verify(printer).print("Something went wrong: file is empty");
    }
}
