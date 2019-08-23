package ru.job4j.set;

import ru.job4j.list.DynamicContainer;

import java.util.Iterator;

/**
 * Сет на основе списка на массиве
 *
 * @param <E> тип хранимых данных
 */
public class SimpleSet<E> implements Iterable<E> {
    private DynamicContainer<E> list;

    public SimpleSet() {
        this.list = new DynamicContainer<>(100);
    }

    @Override
    public Iterator<E> iterator() {
        return list.iterator();
    }

    /**
     * Метод добавляет элемент в сет, сначала проверив его на уникальность
     *
     * @param value значение
     */
    public void add(E value) {
        if (!isDuplicate(value)) {
            list.add(value);
        }
    }

    /**
     * Метод проверяет элементы на уникальность
     *
     * @param value
     * @return
     */
    private boolean isDuplicate(E value) {
        boolean result = false;
        for (E elem : list) {
            if (elem != null && elem.equals(value) || elem == null && value == null) {
                result = true;
                break;
            }
        }
        return result;
    }
}

