package ru.job4j.list;

import java.util.Arrays;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Динамический контейнер на базе массива
 * @param <E> тип данных, которые может хранить
 */
public class DynamicContainer<E> implements Iterable<E> {
    private Object[] container;
    private int position;
    private int modCount;

    /**
     * Конструктор по умолчанию, дефолтный размер встроенного массива 10 элементов
     */
    public DynamicContainer() {
        this.container = new Object[10];
    }

    /**
     * Конструктор с параметром
     * @param initialCapacity начальный размер массива
     */
    public DynamicContainer(int initialCapacity) {
        this.container = new Object[initialCapacity];
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            int expectedModCount = modCount;
            int count;

            @Override
            public boolean hasNext() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                return count < position;
            }

            @Override
            public E next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return (E) container[count++];
            }
        };
    }

    /**
     * Метод добавляет значение в массив, если закончились ячейки, вызывает метод grow()
     * @param value значение
     */
    public void add(E value) {
        if (position > container.length - 1) {
            container = grow();
        }
        container[position++] = value;
        modCount++;
    }

    /**
     * Метод возвращает занчение ячейки по индексу
     * @param index индекс
     * @return значение
     */
    public E get(int index) {
        if (index > position) {
            throw new NoSuchElementException();
        }
        return (E) container[index];
    }

    /**
     * Метод увеличивает текущий массив в 2 раза
     * @return Новый массив
     */
    private Object[] grow() {
        int newLength = container.length * 2;
        return Arrays.copyOf(container, newLength);
    }
}
