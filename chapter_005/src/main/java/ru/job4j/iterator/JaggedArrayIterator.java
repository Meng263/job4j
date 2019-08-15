package ru.job4j.iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Итератор для двумерного массива
 */
public class JaggedArrayIterator implements Iterator<java.lang.Integer> {
    private int[][] values;
    private int i = 0, j = 0;

    public JaggedArrayIterator(int[][] values) {
        this.values = values;
    }

    /**
     * Метод проверяет есть ли следующий элемент
     *
     * @return true, если элемет есть
     */
    @Override
    public boolean hasNext() {
        boolean result = true;
        if (i >= values.length) {
            result = false;
        } else if (j >= values[i].length) {
            result = false;
        }
        return result;
    }

    /**
     * Метод возвращает текущий элемент и сдвигает указатель на следующую ячейку
     *
     * @return текущий элемент
     */
    @Override
    public java.lang.Integer next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        int result = values[i][j];
        j++;
        if (j >= values[i].length) {
            j = 0;
            i++;
        }
        return result;
    }
}
