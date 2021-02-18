package ru.job4j.solid.lsp.parking;

import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class ParkingTest {
    @Test
    public void whenAddThreeSmallAndTwoTrackThanParkingWithSizeTenShouldBeAlmostFull() {
        List<Car> cars = List.of(
                new SmallCar("first_small"),
                new SmallCar("second_small"),
                new SmallCar("third_small"),
                new Track("first_track"),
                new Track("second_track")
        );

        Parking parking = new ParkingImp(10);
        boolean resultAdd = cars
                .stream()
                .allMatch(parking::addCar);

        assertTrue(resultAdd);

        Car anotherCar = new SmallCar("another_small_car");
        Car anotherTrack = new Track("another_track");

        assertEquals(1, parking.getAvailablePlaces(anotherCar));
        assertEquals(0, parking.getAvailablePlaces(anotherTrack));
        assertFalse(parking.addCar(anotherTrack));

        assertTrue(parking.getCars().containsAll(cars));

        Car removedCar = parking.removeCarById("first_small");
        assertEquals(1, removedCar.size());
        assertEquals("another_small_car", removedCar.getId());

    }

}