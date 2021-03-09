package ru.job4j.solid.lsp.parking;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

public class ParkingImp implements Parking {
    private final List<Car> carsSmall = new ArrayList<>();
    private final List<Car> tracks = new ArrayList<>();

    private final int placesSmall;
    private final int placesTrack;

    public ParkingImp(int placesSmall, int placesTrack) {
        this.placesSmall = placesSmall;
        this.placesTrack = placesTrack;
    }

    @Override
    public Collection<Car> getCars() {
        List<Car> cars = new ArrayList<>(carsSmall);
        cars.addAll(tracks);
        return cars;
    }

    @Override
    public boolean addCar(Car car) {
        if (getAvailablePlaces(car) >= 1) {
            if (car.size() == 1) {
                carsSmall.add(car);
            } else {
                if (getAvailablePlacesInTrackParking(car.size()) > 0) {
                    tracks.add(car);
                } else if (getAvailablePlacesForTrack(car.size()) > 0) {
                    carsSmall.add(car);
                }
            }

            return true;
        }
        return false;
    }

    @Override
    public Car removeCarById(String carId) {
        Car result = null;

        Optional<Car> smallCarOptional = carsSmall.stream()
                .filter(car -> car.getId().equals(carId))
                .findFirst();

        if (smallCarOptional.isPresent()) {
            Car car = smallCarOptional.get();
            carsSmall.remove(car);
            result = car;
        } else {
            Optional<Car> tackOptional = carsSmall.stream()
                    .filter(car -> car.getId().equals(carId))
                    .findFirst();

            if (tackOptional.isPresent()) {
                Car car = tackOptional.get();
                tracks.remove(car);
                result = car;
            }
        }
        return result;
    }

    @Override
    public int getAvailablePlaces(Car car) {
        if (car.size() == 1) return
                placesSmall - carsSmall.stream()
                        .map(Car::size)
                        .reduce(Integer::sum)
                        .orElse(0);

        return getAvailablePlacesForTrack(car.size());
    }

    private int getAvailablePlacesForTrack(int trackSize) {
        int availablePlacesInTrackParking = getAvailablePlacesInTrackParking(trackSize);

        if (availablePlacesInTrackParking > 0) {
            return availablePlacesInTrackParking;
        } else {
            return (placesSmall - carsSmall
                    .stream()
                    .map(Car::size)
                    .reduce(Integer::sum).orElse(0)) / trackSize;
        }
    }

    private int getAvailablePlacesInTrackParking(int trackSize) {
        return (placesTrack - tracks.stream()
                .map(Car::size)
                .reduce(Integer::sum).orElse(0)) / trackSize;
    }
}
