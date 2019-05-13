package ru.job4j.tracker;

/**
 * Исключение используется для проверки ввода в заданном диапазоне
 */
public class MenuOutException extends RuntimeException {

    public MenuOutException (String msg) {
        super(msg);
    }
}
