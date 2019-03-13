package ru.job4j.calculator;

/**
 * @author Jora
 * @version 0.5
 */
public class Calculator {

    /**
     *
     * @param first - первый аргумент
     * @param second - второй аргумент
     * @return - возвращает сумму
     */
    public double add(double first, double second) {
        return first + second;
    }

    /**
     *
     * @param first - первый аргумент
     * @param second - второй аргумент
     * @return - возвращает разность
     */
    public double subtract(double first, double second) {
        return first - second;
    }

    /**
     *
     * @param first - первый аргумент
     * @param second - второй аргумент
     * @return - возвращает произведение
     */
    public double multiply(double first, double second) {
        return first * second;
    }

    /**
     *
     * @param first - первый аргумент
     * @param second - второй аргумент
     * @return - возвращает частное
     */
    public double div(double first, double second) {
        return first / second;
    }
}
