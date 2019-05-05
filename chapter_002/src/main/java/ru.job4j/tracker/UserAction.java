package ru.job4j.tracker;

public interface UserAction {

    /**
     * Метод, возвращает ключ опции
     * @return ключ
     */
    int key();

    /**
     * Основной метод.
     * @param input объект типа input
     * @param tracker объект типа tracker
     */
    void execute(Input input, Tracker tracker);

    /**
     * Метод возвращает информацию о данном пункте меню.
     * @return строка меню
     */
    String info();
}
