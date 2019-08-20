package ru.job4j.list;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Контейнер на основе одностороннего связного списка
 *
 * @param <E> тип данных, которые может хранить
 */
public class DynamicLinkedContainer<E> implements Iterable<E> {
    private int position;
    private int modCount;
    private Node<E> first;
    private Node<E> current;

    /**
     * Реализация итератора
     *
     * @return итератор для списка
     */
    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            int expectedModCount = modCount;
            int count;
            Node<E> currentLink = first;

            @Override
            public boolean hasNext() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                return currentLink != null;
            }

            @Override
            public E next() {
                E result;
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                result = currentLink.data;
                currentLink = currentLink.next;
                return result;
            }
        };
    }

    /**
     * Добавление элемента в список
     *
     * @param value значение элемента
     */
    public void add(E value) {
        Node<E> newLink = new Node<E>(value);
        if (this.first == null) {
            this.first = newLink;
            this.current = newLink;
        } else {
            this.current.next = newLink;
            this.current = newLink;
        }
        this.position++;
        this.modCount++;
    }

    /**
     * Метод возвращает значение по индексу
     *
     * @param index индекс
     * @return значение
     */
    public E get(int index) {
        Node<E> currentLink = this.first;
        for (int i = 0; i < index; i++) {
            currentLink = currentLink.next;
        }
        return currentLink.data;
    }

    /**
     * Метод возвращает кол-во элементов списка
     *
     * @return
     */
    public int size() {
        return this.position;
    }

    /**
     * Класс предназначен для хранения данных.
     */
    private static class Node<E> {

        E data;
        Node<E> next;

        Node(E data) {
            this.data = data;
        }
    }

}
