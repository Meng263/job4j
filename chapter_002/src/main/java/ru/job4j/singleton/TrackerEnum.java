package ru.job4j.singleton;

import ru.job4j.tracker.*;

/**
 * Реализация шаблона Singleton через перечисления. Мы не можем создать объект типа enum
 * Eager loading
 */
public enum TrackerEnum {
    INSTANCE;
    /**
     * Класс, в котором содержится логика
     */
    Tracker tracker = new Tracker();
}

