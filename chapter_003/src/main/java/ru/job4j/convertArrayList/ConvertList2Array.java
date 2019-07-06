package ru.job4j.convertArrayList;

import java.util.LinkedList;
import java.util.List;

/**
 * Конвертация списка в двумерный массив
 */
public class ConvertList2Array {
    /**
     * метод конвертирует список в двумерный массив
     *
     * @param list список
     * @param rows кол-во строк
     * @return двумерный массив
     */
    public int[][] toArray(List<Integer> list, int rows) {
        int cells = list.size() % rows == 0 ? list.size() / rows : list.size() / rows + 1;
        int[][] array = new int[rows][cells];
        int x = 0, y = 0;
        for (Integer elem : list) {
            array[y][x++] = elem;
            if (x == cells) {
                x = 0;
                y++;
            }
        }
        return array;
    }

    /**
     * Метод конвертирует список массивов int в общий список
     *
     * @param list лист массивов int
     * @return общий список
     */
    public List<Integer> convert(List<int[]> list) {
        List<Integer> result = new LinkedList<Integer>();
        for (int[] arr : list) {
            for (int elem : arr) {
                result.add(elem);
            }
        }
        return result;
    }
}
