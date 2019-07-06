package ru.job4j.convertArrayList;

import java.util.List;

/**
 * Конвертация списка в двумерный массив
 */
public class ConvertList2Array {
    /**
     * метод конвертирует список в двумерный массив
     * @param list список
     * @param rows кол-во строк
     * @return двумерный массив
     */
    public int[][] toArray(List<Integer> list, int rows) {
        int cells = list.size() % rows == 0 ? list.size() / 3 : list.size() / 3 + 1;
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
}
