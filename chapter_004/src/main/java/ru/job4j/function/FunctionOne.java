package ru.job4j.function;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

/**
 * Подсчет функции в диапазаоне
 */
public class FunctionOne {
    /**
     * Функция считает значения выражения в диапазоне
     *
     * @param start начальное значение
     * @param end   конечное значение
     * @param func  функция
     * @return список значений
     */
    static List<Double> diapason(int start, int end, Function<Double, Double> func) {
        List<Double> list = new ArrayList<>();
        for (int i = start; i < end; i++) {
            list.add(func.apply(i + 0D));
        }
        return list;
    }
}
