package ru.job4j.iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class EvenIterator implements Iterator<Integer> {
    /**
     * каретка
     */
    private int position;
    /**
     * массив занчений
     */
    private final int[] values;
    /**
     * Существует ли следующий элемент
     */
    private boolean isNextElemExists = false;

    /**
     * Конструктор, при создании объекта проверяем существует ли первый элемент и инициализирует поле isNextElemExists
     *
     * @param values массив
     */
    public EvenIterator(int[] values) {
        this.values = values;
        checkEvenNextElem(0);
    }

    /**
     * Метод проверяет есть ли следующий элемент, и сдвигает каретку если он есть.
     *
     * @param displacement смещение первой итерации цикла
     */
    private void checkEvenNextElem(int displacement) {
        for (int i = this.position + displacement; i < values.length; i++) {
            if (values[i] % 2 == 0) {
                this.position = i;
                this.isNextElemExists = true;
                break;
            }
        }
    }

    /**
     * Метод проверяет есть ли следующий элемент
     *
     * @return true, если элемет есть
     */
    @Override
    public boolean hasNext() {
        return this.isNextElemExists;
    }

    /**
     * Метод возвращает текущий элемент и сдвигает указатель на следующую ячейку с четным значением
     * если следующий элемент не найден, просто сдвигает указатель на следующую ячейку
     *
     * @return текущий элемент
     */
    @Override
    public Integer next() {
        if (!this.isNextElemExists) {
            throw new NoSuchElementException();
        }
        int result = this.values[position];
        this.isNextElemExists = false;
        checkEvenNextElem(1);
        if (!this.isNextElemExists) {
            this.position++;
        }
        return result;
    }
}
