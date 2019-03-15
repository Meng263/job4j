package ru.job4j.loop;

/**
 * Вычисление n!
 */

public class Factorial {
    /**
     *
     * @param n число
     * @return факториал
     */

    public int calc(int n) {
        int result = 1;
        for (int i = 1; i <= n; i++) {
            result = result * i;
        }
        return result;

    }
}