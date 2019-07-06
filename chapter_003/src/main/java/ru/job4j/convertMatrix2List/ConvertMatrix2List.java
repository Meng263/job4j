package ru.job4j.convertMatrix2List;

import java.util.ArrayList;
import java.util.List;

/**
 * Конвертация двумерного массива в список
 */
public class ConvertMatrix2List {
    /**
     * Метод конвертирует двумерный массив в список
     * @param array двумерный массив типа int
     * @return list типа Integer
     */
    public List<Integer> toList(int[][] array) {
        List<Integer> list = new ArrayList<>(array.length * array[0].length);
        for (int[] y: array) {
            for (int x: y) {
                list.add(x);
            }
        }
        return list;
    }
}