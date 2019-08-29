package ru.job4j.tree;

import java.util.*;

/**
 * Реализация простого дерева
 * @param <E> тип занчения
 */
public class Tree<E extends Comparable<E>> implements SimpleTree<E> {
    /**
     * Корень
     */
    private Node<E> root;

    /**
     * Конструктор
     * @param value начальное занчение, храниться в корне
     */
    public Tree(E value) {
        this.root = new Node<>(value);
    }

    /**
     * Добавить элемент child в parent.
     * Parent может иметь список child.
     * @param parent parent.
     * @param child child.
     * @return true, если вставка успешна
     */
    @Override
    public boolean add(E parent, E child) {
        boolean result = false;
        Optional<Node<E>> knot = findBy(parent);
        if (knot.isPresent()) {
            knot.get().add(new Node<>(child));
            result = true;
        }
        return result;
    }

    /**
     * Метод ищет узел по значению
     * используется обход в ширину
     * @param value значение
     * @return узел, обернутый в Optional
     */
    @Override
    public Optional<Node<E>> findBy(E value) {
        Optional<Node<E>> rsl = Optional.empty();
        Queue<Node<E>> data = new LinkedList<>();
        data.offer(this.root);
        while (!data.isEmpty()) {
            Node<E> el = data.poll();
            if (el.eqValue(value)) {
                rsl = Optional.of(el);
                break;
            }
            for (Node<E> child : el.leaves()) {
                data.offer(child);
            }
        }
        return rsl;
    }

    /**
     * Итератор для дерева
     * @return итератор
     */
    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            Queue<Node<E>> queue = new LinkedList<>(Arrays.asList(root));
            @Override
            public boolean hasNext() {
                return !queue.isEmpty();
            }

            @Override
            public E next() {
                Optional<Node<E>> knot = Optional.ofNullable(queue.poll());
                queue.addAll(knot.get().leaves());
                return knot.get().getValue();
            }
        };
    }
}
