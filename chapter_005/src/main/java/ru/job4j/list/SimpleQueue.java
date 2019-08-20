package ru.job4j.list;

/**
 * Очередь на основе двух стеков
 *
 * @param <T> тип данных
 */
public class SimpleQueue<T> {
    private SimpleStack<T> mainStack;
    private SimpleStack<T> pollStack;

    /**
     * Конструктор
     */
    public SimpleQueue() {
        this.mainStack = new SimpleStack<>();
        this.pollStack = new SimpleStack<>();
    }

    /**
     * Метод извлекает первый элемент из очереди.
     *
     * @return значение
     */
    public T poll() {
        if (pollStack.isEmpty()) {
            int mainStackSize = mainStack.size();
            for (int i = 0; i < mainStackSize; i++) {
                pollStack.push(mainStack.poll());
            }
        }
        return pollStack.poll();
    }

    /**
     * Метод вставляет элемент в очередь
     *
     * @param value значение
     */
    public void push(T value) {
        this.mainStack.push(value);
    }

    /**
     * Метод возвращает размер очереди
     *
     * @return размер очереди
     */
    public int size() {
        return mainStack.size() + pollStack.size();
    }

    /**
     * Метод возвращает true, если очередь пуста
     *
     * @return true, если очередь пуста
     */
    public boolean isEmpty() {
        return this.size() == 0;
    }
}
