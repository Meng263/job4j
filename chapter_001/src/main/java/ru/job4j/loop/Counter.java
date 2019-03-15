package ru.job4j.loop;

/**
 * Cумма четных чисел диапазона
 */
public class Counter {
    /**
     *
     * @param start от
     * @param finish до
     * @return сумма
     */
    public int add(int start, int finish) {
        int sum = 0;

        for (int i = start; i <= finish; i++) {
            if (i % 2 == 0) {
                sum = sum + i;
            }
        }
        return sum;
    }

}