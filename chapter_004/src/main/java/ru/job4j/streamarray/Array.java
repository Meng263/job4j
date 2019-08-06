package ru.job4j.streamarray;

import java.util.Arrays;

/**
 * Тестовое задание Stream API
 */
public class Array {
    /**
     * Метод фильтрует массив, оставляет только четные числа
     * @param arr массив
     * @return отфильтрованный массив
     */
    public int[] filterEven(int[] arr) {
        return Arrays.stream(arr)
                .filter(elem -> elem % 2 == 0)
                .toArray();
    }

    /**
     * Метод возводит каждый элемент массива в квадрат
     * @param arr массив
     * @return массив квадратов
     */
    public int[] squareOfNumbers(int[] arr) {
        return Arrays
                .stream(arr).map(elem -> elem * elem)
                .toArray();
    }

    /**
     * Метод суммирует все элементы массива
     * @param arr массив
     * @return сумма элементов
     */
    public int sumOfNumbers(int[] arr) {
        return Arrays
                .stream(arr)
                .reduce(0, Integer::sum);
    }
}
