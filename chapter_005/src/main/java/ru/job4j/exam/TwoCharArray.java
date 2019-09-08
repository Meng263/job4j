package ru.job4j.exam;

import java.util.HashSet;
import java.util.Set;

/**
 * Решение задачи
 * 2 массива char, нужно проверить что 2 массива состоят из одинаковых символов, не важна последовательность.
 */
public class TwoCharArray {
    /**
     * метод проверяет 2 массива символов на идентичность.
     * @param left левый массив
     * @param right правый массив
     * @return true, если массивы содержат одинаковые элементы
     */
    public boolean checkCharArrays(char[] left, char[] right) {
        boolean result = true;
        Set<Character> set = new HashSet<>();
        if (left.length == right.length) {
            for (char elem : left) {
                set.add(elem);
            }
            for (char elem : right) {
                if (set.add(elem)) {
                    result = false;
                    break;
                }
            }
        } else {
            result = false;
        }
        return result;
    }
}
