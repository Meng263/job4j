package ru.job4j.tracker;

/**
 * Класс реализует общую логику классов-действий.
 */
public abstract class BaseAction implements UserAction {
    final int key;
    final String info;

    /**
     * Конструктор, который требует явной инициализации параметров
     *
     * @param key  ключ
     * @param info описание действия
     */
    protected BaseAction(int key, String info) {
        this.key = key;
        this.info = info;
    }

    @Override
    /**
     * @return возвращает ключ
     */
    public int key() {
        return this.key;
    }

    @Override
    /**
     * Метод возвращает информацию о операщии
     *
     * @return инфо
     */
    public String info() {
        return String.format("%s. %s", this.key, this.info);
    }

    @Override
    /**
     * Метод реализует основную логику действия
     *
     *
     * @param input   объект типа input
     * @param tracker объект типа tracker
     */
    public abstract void execute(Input input, Tracker tracker);
}
