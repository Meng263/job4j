package ru.job4j.pstrategy;

/**
 * Квадрат
 */
public class Square implements Shape {
    /**
     * Реализация метода для отрисовки квадрата
     *
     * @return Квадрат в псевдографике
     */
    @Override
    public String draw() {
        StringBuilder builder = new StringBuilder();
        builder.append("@@@@@@\n");
        builder.append("@    @\n");
        builder.append("@    @\n");
        builder.append("@    @\n");
        builder.append("@    @\n");
        builder.append("@@@@@@\n");
        return builder.toString();
    }
}
