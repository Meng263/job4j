package ru.job4j.singleton;

import ru.job4j.tracker.Tracker;

/**
 * Реализация шаблона Singleton через приватную статическую константу
 * Eager loading
 */
public class TrackerStaticFinalField {
    /**
     * Константа, содержит единственный экземпляр класса
     */
    private static final TrackerStaticFinalField INSTANCE = new TrackerStaticFinalField();

    private TrackerStaticFinalField() {
    }

    /**
     * Метод возвращает единственный экземпляр класса
     *
     * @return INSTANCE
     */
    public static TrackerStaticFinalField getInstance() {
        return INSTANCE;
    }

    /**
     * Класс, который содержит логику
     */
    Tracker tracker = new Tracker();
}
