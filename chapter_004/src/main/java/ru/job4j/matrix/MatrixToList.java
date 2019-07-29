package ru.job4j.matrix;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Преобразование двумерного массива в список
 */
public class MatrixToList {
    /**
     * Метод конвертирует двумерный массив Integer в список Integer
     * @param matrix Integer[][]
     * @return List<Integer>
     */
    List<Integer> convert(Integer[][] matrix) {
        return Stream.of(matrix).flatMap(Stream::of).collect(Collectors.toList());
    }
}
