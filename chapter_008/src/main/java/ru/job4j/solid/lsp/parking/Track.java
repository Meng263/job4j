package ru.job4j.solid.lsp.parking;

public class Track implements Car {
    private final String id;
    private final int size;

    public Track(String id, int size) {
        this.id = id;
        this.size = size;
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public int size() {
        return size;
    }
}
