package ru.job4j.solid.lsp.parking;

public class SmallCar implements Car {
    private final String id;

    public SmallCar(String id) {
        this.id = id;
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public int size() {
        return 1;
    }
}
