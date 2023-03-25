package org.example;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class CarsByDateTest {

    @Test
    void testToString() {
        // given
        CarsByDate carsByDate = new CarsByDate(LocalDate.of(2021, 10, 10), 12);

        // when
        String actual = carsByDate.toString();

        // then
        String expected = "2021-10-10 12";
        Assertions.assertEquals(expected, actual);
    }
}
