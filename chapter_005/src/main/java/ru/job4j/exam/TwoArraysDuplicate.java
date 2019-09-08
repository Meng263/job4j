package ru.job4j.exam;

import java.util.*;

/**
 * Решение задачи
 * дано 2 массива с числами, вернуть общие элементы
 */
public class TwoArraysDuplicate {
    /**
     *  метод возвращает общие элементы 2 массивов используя сет
     * @param left левый массив
     * @param right правый массив
     * @return массив с общими элементами
     */
    public Integer[] checkDuplicate(Integer[] left, Integer[] right) {
        List<Integer> list = new ArrayList<>(100);
        Set<Integer> set = new HashSet<>();
        for (int elem : left) {
            set.add(elem);
        }
        for (int elem : right) {
           if (!set.add(elem)) {
             list.add(elem);
           }
        }
        Integer[] result = list.toArray(new Integer[0]);
        return result;
    }
}
