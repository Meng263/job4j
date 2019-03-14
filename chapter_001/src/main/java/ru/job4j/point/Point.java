package ru.job4j.point;

/**
 * Вычисление расстояния между двумя точками на координатной плоскости
 */
public class Point {
    /**
     *
     * @param x1 координата оси абсцисс 1 точки
     * @param y1 координата оси ординат 1 точки
     * @param x2 координата оси абсцисс 2 точки
     * @param y2 координата оси ординат 2 точки
     * @return расстояние по модулю
     */
    public double distance(int x1, int y1, int x2, int y2) {
        return Math.sqrt((Math.pow((x2 - x1), 2) + Math.pow((y2 - y1), 2)));
    }
}
