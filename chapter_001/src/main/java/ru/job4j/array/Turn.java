package ru.job4j.array;

/**
 * Перевернуть Массив
 */
public class Turn {
    /**
     * 
     * @param array исходный массив
     * @return перевернутый массив
     */
    public int[] back(int[] array) {
        int tmp = 0;
        for (int i = 0; i < array.length / 2; i++) {
            tmp = array[i];
            array[i] = array[array.length-1-i];
            array[array.length-1-i] = tmp;
        }

        return array;
    }
}
