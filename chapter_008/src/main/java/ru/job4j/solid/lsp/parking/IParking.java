package ru.job4j.solid.lsp.parking;

import java.util.Collection;

public interface IParking {
    Collection<ICar> getCars();

    boolean addCar(ICar car);

    ICar removeCarById(String carId);

    int getAvailablePlacesOfType(ICar car);
}
