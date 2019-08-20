package ru.job4j.list;

/**
 * Очередь на основе двух стеков
 *
 * @param <T> тип данных
 */
public class SimpleQueue<T> {
    private SimpleStack<T> mainStack;
    private SimpleStack<T> pollStack;
    private int position;

    /**
     * Конструктор
     */
    public SimpleQueue() {
        this.mainStack = new SimpleStack<>();
        this.pollStack = new SimpleStack<>();
    }

    /**
     * Метод извлекает первый элемент из очереди.
     * Для этого он извлекает все элементы из mainStack и вставляет в pollStack,
     * тем самым стек переворачивается.
     * Первый извлеченный элемент из pollStack оказывается первым для очереди.
     * Уменьшается счетчик и все элементы добавляются в mainStack обратно.
     *
     * @return значение
     */
    public T poll() {
        for (int i = 0; i < position; i++) {
            pollStack.push(mainStack.poll());
        }
        this.position--;
        T result = pollStack.poll();
        for (int i = 0; i < position; i++) {
            mainStack.push(pollStack.poll());
        }
        return result;
    }

    /**
     * Метод вставляет элемент в очередь
     *
     * @param value значение
     */
    public void push(T value) {
        this.mainStack.push(value);
        this.position++;
    }

    private SimpleStack<T> reverseStack() {

        return null;
    }
}
