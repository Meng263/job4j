package ru.job4j.array;

/**
 * Проверка массива на истинность или ложность всех элементов одновременно
 */
public class Check {
    /**
     * @param data массив
     * @return Истина, если все элементы одинаковые иначе ложь
     */
    public boolean mono(boolean[] data) {
        boolean result = true;
        for (int i = 0; i < data.length; i++) {
            if (data[0] != data[i]) {
                result = false;
                break;
            }
        }
        return result;
    }
}
