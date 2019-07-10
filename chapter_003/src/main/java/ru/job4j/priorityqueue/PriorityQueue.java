package ru.job4j.priorityqueue;

import java.util.*;

/**
 * Очередь задач с приоритетами, добавляет новую задачу в нужную позицию в соответсвии с приоритетом.
 * Извлекается задача с наибольшим приоритетом
 */
public class PriorityQueue {
    private LinkedList<Task> tasks = new LinkedList<>();

    /**
     * Метод вставляет в нужную позицию элемент.
     * Позиция определяется по полю приоритет, чем меньшее занчение, тем выше приоритет
     *
     * @param task задача
     */
    public void put(Task task) {
        int count = 0;
        if (tasks.isEmpty()) {
            tasks.add(task);
        } else {
            Iterator<Task> iterator = tasks.listIterator();
            while (iterator.hasNext()) {
                Task element = iterator.next();
                if (element.getPriority() >= task.getPriority()) {
                    break;
                }
                count++;
            }
            tasks.add(count, task);
        }
    }

    /**
     * Метод извлекает задачу с наибольшим приорететом
     *
     * @return задача
     */
    public Task take() {
        return this.tasks.poll();
    }
}