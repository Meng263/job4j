package ru.job4j.list;

/**
 * Стек
 * @param <T> тип данных
 */
public class SimpleStack<T> {
    private SimpleLinkedList<T> list;

    public SimpleStack() {
        this.list = new SimpleLinkedList<T>();
    }

    /**
     * Метод извлекает элемент из стека
     * @return значение
     */
    public T poll() {
        return list.delete();
    }

    /**
     * Метод добавляет элемент в стек
     * @param value значение
     */
    public void push(T value) {
       list.add(value);
    }
}
