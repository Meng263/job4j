package ru.job4j.singleton;

import ru.job4j.tracker.Tracker;

/**
 * Реализация шаблона Singleton через создание вложенного класса
 * Lazy loading
 */
public class TrackerStaticFinalClass {
    /**
     * Приватный конструктор по-умолчанию
     */
     private TrackerStaticFinalClass() { }

    /**
     * Метод возвращает единственный экземпляр класса
     * @return instance
     */
    public static TrackerStaticFinalClass getInstance() {
        return Holder.INSTANCE;
    }

    /**
     * Класс, который содержит логику
     */
    Tracker tracker = new Tracker();

    /**
     * Внутренний класс
     */
    private static final class Holder {
        /**
         * Константа, содержит единственный экземпляр внешнего класса
         */
        private static final TrackerStaticFinalClass INSTANCE = new TrackerStaticFinalClass();
    }

}

