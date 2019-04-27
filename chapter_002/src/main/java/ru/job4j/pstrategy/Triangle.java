package ru.job4j.pstrategy;

/**
 * Треугольник
 */
public class Triangle implements Shape {
    /**
     * Реализация метода для отрисовки треугольника
     *
     * @return треугольник в псевдографике
     */
    @Override
    public String draw() {
        StringBuilder builder = new StringBuilder();
        builder.append("@    \n");
        builder.append("@@@  \n");
        builder.append("@@@@ \n");
        builder.append("@@@@@@\n");
        return builder.toString();
    }
}
