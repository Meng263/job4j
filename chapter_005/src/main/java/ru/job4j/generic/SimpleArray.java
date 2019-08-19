package ru.job4j.generic;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Универсальная обертка над массивом
 * @param <T> тип данных, который хранит массив
 */
public class SimpleArray<T> implements Iterable<T> {
    private final Object[] values;
    private int count;

    /**
     * Конструктор
     * @param elements число элеметов массива
     */
    public SimpleArray(int elements) {
        this.values = new Object[elements];
    }

    /**
     * Итератор
     * @return итератор
     */
    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private int index = 0;

            @Override
            public boolean hasNext() {
                return index < count;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return (T) values[index++];
            }
        };
    }

    /**
     * Метод добавлеят элемент, если закончились ячейки кидет исключение
     * @param model элемет
     */
    public void add(T model) {
        if (count > values.length - 1) {
            throw new NoSuchElementException();
        }
        values[count++] = model;
    }

    /**
     * Метод заменяет элемент в ячейке массива
     * @param index индекс
     * @param model значение, на кторое нужно заменить
     */
    public void set(int index, T model) {
        if (index > count) {
            throw new NoSuchElementException();
        }
        values[index] = model;
    }

    /**
     * Метод удаляет элемент из массива и сдвигает его
     * @param index индекс удаляемого элемента
     */
    public void remove(int index) {
        if (index > count) {
            throw new NoSuchElementException();
        }
        for (int i = index; i < values.length - 1; i++) {
            values[i] = values[i + 1];
        }
    }

    /**
     * Метод возвращает значение по индексу
     * @param index индекс
     * @return значение
     */
    public T get(int index) {
        if (index > count) {
            throw new NoSuchElementException();
        }
        return (T) values[index];
    }
}
