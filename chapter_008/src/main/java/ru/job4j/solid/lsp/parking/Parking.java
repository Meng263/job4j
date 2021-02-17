package ru.job4j.solid.lsp.parking;

import java.util.Collection;

public interface Parking {
    Collection<Car> getCars();

    boolean addCar(Car car);

    Car removeCarById(String carId);

    int getAvailablePlaces(Car car);
}
