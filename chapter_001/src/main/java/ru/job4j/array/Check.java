package ru.job4j.array;

/**
 * Проверка массива на истинность или ложность всех элементов одновременно
 */
public class Check {
    /**
     *
     * @param data массив
     * @return Истина, если все элементы одинаковые иначе ложь
     */
    public boolean mono(boolean[] data) {
        boolean result = false;
        int tru = 0; // счетчик true
        int fals = 0; // счетчик false
        for (int i = 0; i < data.length; i++) {
            if (data[i]) tru++;
            else fals++;
        }
       if ((tru == data.length) || (fals == data.length)) result = true;
            return result;
    }
}
