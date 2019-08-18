package ru.job4j.list;

/**
 * Класс SimpleLinkedList.
 */
public class SimpleLinkedList<E> {

    private int size;
    private Node<E> first;


    /**
     * Метод возвращает true, если список пуст
     *
     * @return true, если список пуст
     */
    public boolean isEmpty() {
        boolean result = false;
        if (size == 0) {
            result = true;
        }
        return result;
    }

    /**
     * Метод вставляет в начало списка данные.
     */
    public void add(E data) {
        Node<E> newLink = new Node<>(data);
        newLink.next = this.first;
        this.first = newLink;
        this.size++;
    }

    /**
     * Метод удаляет первый элемент в списке
     *
     * @return значение удаляемого элемента, null если нет элемента для удаления
     */
    public E delete() {
        E result = null;
        if (!isEmpty()) {
            result = this.first.data;
            this.first = this.first.next;
            this.size--;
        }
        return result;
    }

    /**
     * Метод получения элемента по индексу.
     */
    public E get(int index) {
        Node<E> result = this.first;
        for (int i = 0; i < index; i++) {
            result = result.next;
        }
        return result.data;
    }

    /**
     * Метод получения размера коллекции.
     */
    public int getSize() {
        return this.size;
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