package ru.job4j.tree;

import java.util.ArrayList;
import java.util.List;

/**
 * Узел дерева
 * @param <E> тип значения
 */
public class Node<E extends Comparable<E>> {
    /**
     * Список подъузлов
     */
    private final List<Node<E>> children = new ArrayList<>();
    /**
     * Хранимое значение
     */
    private final E value;

    public Node(final E value) {
        this.value = value;
    }

    public E getValue() {
        return value;
    }

    /**
     * Метод добавляет подъузел в список подъузлов
     * @param child подъузел
     */
    public void add(Node<E> child) {
        this.children.add(child);
    }

    /**
     * Метод возвращает список подъузлов
     * @return список подъузлов
     */
    public List<Node<E>> leaves() {
        return this.children;
    }

    /**
     * Метод проверяет эквивалентность значения со значением, хранящимся в узле
     * @param that проверяемое значение
     * @return true, если значения эквивалентны
     */
    public boolean eqValue(E that) {
        return this.value.compareTo(that) == 0;
    }
}