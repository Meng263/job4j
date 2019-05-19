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
    private static final TrackerStaticFinalField instance = new TrackerStaticFinalField();

    private TrackerStaticFinalField() {
    }

    /**
     * Метод возвращает единственный экземпляр класса
     *
     * @return instance
     */
    public static TrackerStaticFinalField getInstance() {
        return instance;
    }

    /**
     * Класс, который содержит логику
     */
    Tracker tracker = new Tracker();
}
