package ru.job4j.solid.isp.second;

public class Duck implements IDuck {
    @Override
    public String fly() {
        return "Fly!... ";
    }

    @Override
    public String quack() {
        return "Quack! Quack!";
    }

    @Override
    public String swim() {
        return "Swim! ....";
    }
}
