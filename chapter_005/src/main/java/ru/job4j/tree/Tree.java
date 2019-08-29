package ru.job4j.tree;

import java.util.*;

/**
 * Реализация простого дерева
 *
 * @param <E> тип занчения
 */
public class Tree<E extends Comparable<E>> implements SimpleTree<E> {
    /**
     * Корень
     */
    private Node<E> root;
    private int modCount;

    /**
     * Конструктор
     *
     * @param value начальное занчение, храниться в корне
     */
    public Tree(E value) {
        this.root = new Node<>(value);
        modCount = 0;
    }

    /**
     * Добавить элемент child в parent.
     * Parent может иметь список child.
     *
     * @param parent parent.
     * @param child  child.
     * @return true, если вставка успешна
     */
    @Override
    public boolean add(E parent, E child) {
        boolean result = false;
        Optional<Node<E>> knot = findBy(parent);
        if (knot.isPresent()) {
            knot.get().add(new Node<>(child));
            result = true;
            modCount++;
        }
        return result;
    }

    /**
     * Метод ищет узел по значению
     * используется обход в ширину
     *
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
     *
     * @return итератор
     */
    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            int expectedModCount = modCount;
            Queue<Node<E>> queue = new LinkedList<>(Arrays.asList(root));

            @Override
            public boolean hasNext() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                return !queue.isEmpty();
            }

            @Override
            public E next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                E result = null;
                Optional<Node<E>> knot = Optional.ofNullable(queue.poll());
                if (knot.isPresent()) {
                    queue.addAll(knot.get().leaves());
                    result = knot.get().getValue();
                }
                return result;
            }
        };
    }
}
