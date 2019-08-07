package ru.job4j.streamarray;

import java.util.Arrays;

/**
 * Тестовое задание Stream API
 */
public class Array {
    /**
     * Метод фильтрует массив, оставляет только четные числа, замет возводит в квадрат, затем суммирует числа
     *
     * @param arr массив
     * @return итоговая сумма
     */
    public int doStreamArray(int[] arr) {
        return Arrays.stream(arr)
                .filter(elem -> elem % 2 == 0)
                .map(elem -> elem * elem)
                .reduce(0, Integer::sum);
    }
}
