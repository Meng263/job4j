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
            new Track("first_track", 3),
            new Track("second_track", 3)
    );

    @Test
    public void whenAddCarsThanGetItShouldBeEquals() {
        int countSmall = 4;
        int countTrack = 2;
        int sizeTrack = 3;
        Parking parking = new ParkingImp(countSmall, countTrack, sizeTrack);
        boolean allAdded = cars.stream().allMatch(parking::addCar);

        assertTrue(allAdded);
        assertTrue(parking.getCars().containsAll(cars));
    }

    @Test
    public void addCarsAlmostToFullThanCanAddSmallCanNoTrack() {
        int countSmall = 4;
        int countTrack = 2;
        int sizeTrack = 3;
        Parking parking = new ParkingImp(countSmall, countTrack, sizeTrack);
        boolean resultAdd = cars
                .stream()
                .allMatch(parking::addCar);

        assertTrue(resultAdd);
        int availableSizeTrack = parking.getAvailablePlaces(new Track("another track", sizeTrack));
        int availableSizeSmall = parking.getAvailablePlaces(new SmallCar("another small car"));
        assertEquals(availableSizeTrack, 0);
        assertEquals(availableSizeSmall, 1);
    }

    @Test
    public void whenAddCarAndRemoveThanRemoved() {
        int countSmall = 4;
        int countTrack = 2;
        int sizeTrack = 3;
        Parking parking = new ParkingImp(countSmall, countTrack, sizeTrack);
        String smallCarId = "first_small";
        Car smallCar = new SmallCar(smallCarId);
        parking.addCar(smallCar);
        Car removedCar = parking.removeCarById(smallCarId);
        assertEquals(1, removedCar.size());
        assertEquals(smallCarId, removedCar.getId());
    }

    @Test
    public void parkOneTrackOnPlaceOnThreeSmall() {
        int countSmall = 3;
        int countTrack = 0;
        int sizeTrack = 3;
        Parking parking = new ParkingImp(countSmall, countTrack, sizeTrack);
        Track track = new Track("track", sizeTrack);
        boolean added = parking.addCar(track);
        assertTrue(added);
        assertEquals(parking.getAvailablePlaces(track), 0);
    }
}