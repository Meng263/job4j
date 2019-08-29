package ru.job4j.tree;

import java.util.Optional;

/**
 * Интерфейс простого дерева
 * @param <E> тип заначения
 */
public interface SimpleTree<E extends Comparable<E>> extends Iterable<E> {
    /**
     * Добавить элемент child в parent.
     * Parent может иметь список child.
     * @param parent parent.
     * @param child child.
     * @return true, если вставка успешна
     */
    boolean add(E parent, E child);

    /**
     * Метод ищет узел по значению
     * @param value значение
     * @return узел, обернутый в Optional
     */
    Optional<Node<E>> findBy(E value);
}