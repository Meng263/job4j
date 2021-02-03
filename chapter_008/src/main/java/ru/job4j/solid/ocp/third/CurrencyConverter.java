package ru.job4j.solid.ocp.third;

public class CurrencyConverter {
    public double convertDollarToRub(double dollar) {
        return dollar * new CourseProvider().getCourse();
    }

    private static class CourseProvider {
        double getCourse() {
            return 75.5;
        }
    }

}
