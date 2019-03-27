package ru.job4j.array;

/**
 * Поиск элемента в массиве
 */
public class FindLoop {
    /**
     *
     * @param data массив
     * @param el элемент, который ищем
     * @return индекс элемента, который нашли
     */
    public int indexOf(int[] data, int el) {
        int rst = -1;
        for (int index = 0; index < data.length; index++) {
            if (data[index] == el) {
                rst = index;
                break;
            }
        }
        return rst;
    }
}