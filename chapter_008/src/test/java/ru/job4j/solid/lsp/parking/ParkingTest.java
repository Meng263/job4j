package ru.job4j.solid.lsp.parking;

import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ParkingTest {
    List<Car> cars = List.of(
            new SmallCar("first_small"),
            new SmallCar("second_small"),
            new SmallCar("third_small"),
            new Track("first_track"),
            new Track("second_track")
    );

    @Test
    public void whenAddCarsThanGetItShouldBeEquals() {
        int countSmall = 4;
        int countTrack = 2;
        Parking parking = new ParkingImp(countSmall, countTrack);
        cars.stream().allMatch(parking::addCar);

        assertTrue(parking.getCars().containsAll(cars));
    }

    @Test
    public void addCarsAlmostToFullThanCanAddSmallCanNoTrack() {
        int countSmall = 4;
        int countTrack = 2;
        Parking parking = new ParkingImp(countSmall, countTrack);
        boolean resultAdd = cars
                .stream()
                .allMatch(parking::addCar);

        assertTrue(resultAdd);
        int availableSizeTrack = parking.getAvailablePlaces(new Tack("another track"));
        int availableSizeSmall = parking.getAvailablePlaces(new Tack("another track"));
        assertEquals(availableSizeTrack, 0);
        assertEquals(availableSizeSmall, 1);
    }

    @Test
    public void whenAddCarAndRemoveThanRemoved() {
        int countSmall = 4;
        int countTrack = 2;
        Parking parking = new ParkingImp(countSmall, countTrack);
        String smallCarId = "first_small";
        Car smallCar = new SmallCar(smallCarId);
        parking.addCar(smallCar);
        Car removedCar = parking.removeCarById(smallCarId);
        assertEquals(1, removedCar.size());
        assertEquals(smallCarId, removedCar.getId());
    }

}