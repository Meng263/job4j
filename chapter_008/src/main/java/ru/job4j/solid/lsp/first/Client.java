package ru.job4j.solid.lsp.first;

public class Client {

    boolean areaVerifier(Rectangle rectangle) {
        rectangle.setHeight(5);
        rectangle.setWidth(4);

        if (rectangle.area() != 20) throw new IllegalStateException("Bad area!");

        return true;
    }
}
