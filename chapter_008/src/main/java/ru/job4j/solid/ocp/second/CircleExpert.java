package ru.job4j.solid.ocp.second;

public class CircleExpert {
    private CircleExpert() {
    }

    private static CircleExpert instance = null;

    private double pi = 3.14;

    public static CircleExpert getInstance() {
        instance = instance != null ? instance : new CircleExpert();
        return instance;
    }

    public double getAreaOfCircle(double radius) {
        return radius * radius * pi;
    }
}
