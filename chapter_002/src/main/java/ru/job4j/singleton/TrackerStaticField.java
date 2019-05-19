package ru.job4j.singleton;

import ru.job4j.tracker.Tracker;

/**
 * Реализация шаблона Singleton через приватную статическую переменную
 * Lazy loading
 */
public class TrackerStaticField {
    /**
     * Переменная, содержит единственный экземпляр класса
     */
    private static TrackerStaticField instance;

    /**
     * Приватный конструктор
     */
    private TrackerStaticField() {}

    /**
     * Метод возвращает единственный экземпляр класса
     * @return instance
     */
    public static TrackerStaticField getInstance() {
        if (instance == null) {
            instance = new TrackerStaticField();
        }
        return instance;
    }

    /**
     * Класс, который содержит логику
     */
    Tracker tracker = new Tracker();

}
