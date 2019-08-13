package ru.job4j.iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class EvenIterator implements Iterator<Integer> {
    /**
     * каретка
     */

    private int position = -1;
    /**
     * массив занчений
     */

    private final int[] values;
    /**
     * Вызывался ли метод Next, чтобы при многократном вызове метода hasNext не сдигать каретку лишний раз
     */

    private boolean isNext = false;

    /**
     * Конструктор
     *
     * @param values массив
     */
    public EvenIterator(int[] values) {
        this.values = values;
    }

    /**
     * Метод проверяет есть ли следующий элемент
     *
     * @return true, если элемет есть
     */
    @Override
    public boolean hasNext() {
        /**
         * Существует ли следующий элемент
         */
        boolean result = false;
        for (int i = this.position + 1; i < values.length; i++) {
            if (values[i] % 2 == 0) {
                result = true;
                if (isNext) {
                    this.position = i;
                    isNext = false;
                }
                break;
            }
        }
        return result;
    }

    /**
     * Метод возвращает текущий элемент и сдвигает указатель на следующую ячейку с четным значением
     * если следующий элемент не найден, просто сдвигает указатель на следующую ячейку
     *
     * @return текущий элемент
     */
    @Override
    public Integer next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        this.isNext = true;
        if (!hasNext()) {
            this.position++;
        }
        return this.values[position];
    }
}
