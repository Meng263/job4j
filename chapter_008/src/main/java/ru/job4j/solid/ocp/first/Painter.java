package ru.job4j.solid.ocp.first;

public class Painter {
    private int count = 10;

    public void drawHare() {
        for (int i = 0; i < count; i++) {
            System.out.println("(\\___/)");
            System.out.println("(='.'=)");
            System.out.println("(\")_(\")");
            System.out.println();
        }
    }
}
