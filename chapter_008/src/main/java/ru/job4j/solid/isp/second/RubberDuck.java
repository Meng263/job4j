package ru.job4j.solid.isp.second;

public class RubberDuck implements IDuck {
    @Override
    public String fly() {
        throw new UnsupportedOperationException("I can't fly!!");
    }

    @Override
    public String quack() {
        return "Quack!";
    }

    @Override
    public String swim() {
        return "Swim!";
    }
}
