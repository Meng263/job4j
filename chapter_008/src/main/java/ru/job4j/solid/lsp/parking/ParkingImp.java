package ru.job4j.solid.lsp.parking;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

public class ParkingImp implements Parking {
    private final List<Car> cars = new ArrayList<>();
    private final int countSmall;
    private final int countTrack;
    private final int sizeTrack;
    private int sizeOccupied = 0;

    public ParkingImp(int countSmall, int countTrack, int sizeTrack) {
        this.countSmall = countSmall;
        this.countTrack = countTrack;
        this.sizeTrack = sizeTrack;
    }

    @Override
    public Collection<Car> getCars() {
        return cars;
    }

    @Override
    public boolean addCar(Car car) {

        if (getAvailablePlaces(car) >= 1) {
            cars.add(car);
            sizeOccupied += car.size();
            return true;
        }
        return false;
    }

    @Override
    public Car removeCarById(String carId) {
        Car result = null;

        Optional<Car> carOptional = cars.stream()
                .filter(car -> car.getId().equals(carId))
                .findFirst();

        if (carOptional.isPresent()) {
            Car car = carOptional.get();
            cars.remove(car);
            sizeOccupied -= car.size();
            result = car;
        }
        return result;
    }

    @Override
    public int getAvailablePlaces(Car car) {
        return ((countSmall + countTrack * sizeTrack) - sizeOccupied) / car.size();
    }
}
