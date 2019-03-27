package ru.job4j.array;

import java.util.Arrays;
import java.util.Collections;

/**
 * удаляем дубликаты в массиве
 */
public class ArrayDuplicate {
    /**
     *
     * @param array исходный массив
     * @return массив с удаленными дубликатами
     */
    public String[] remove(String[] array){

        for (int i = array.length - 1; i > 0; i--) {
            for (int j = 0; j < i; j++) {
                if (array[i].equals(array[j])){
                    array[j] = "";
                }
            }
        }
        Arrays.sort(array, Collections.reverseOrder());
        int length = 0;
        for (int i = 0; i < array.length; i++) {
            if (array[i].equals("")){
                length = i;
                break;
            }
        }
    return Arrays.copyOf(array, length);}
}
