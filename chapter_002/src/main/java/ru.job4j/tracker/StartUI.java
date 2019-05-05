package ru.job4j.tracker;


/**
 * @version $Id$
 * @since 0.1
 */
public class StartUI {

    /**
     * Получение данных от пользователя.
     */
    private final Input input;

    /**
     * Хранилище заявок.
     */
    private final Tracker tracker;

    /**
     * Конструтор инициализирующий поля.
     *
     * @param input   ввод данных.
     * @param tracker хранилище заявок.
     */
    public StartUI(Input input, Tracker tracker) {
        this.input = input;
        this.tracker = tracker;
    }

    /**
     * Основой цикл программы.
     */
    public void init() {

        MenuTracker menu = new MenuTracker(this.input, this.tracker);
        menu.fillActions();
        int ext = 0;
        do {
            menu.show();
            ext = Integer.valueOf(input.ask("Select:"));
            menu.select(ext);
        } while (ext != 6);
    }
    /**
     * Запускт программы.
     *
     * @param args
     */
    public static void main(String[] args) {
        new StartUI(new ConsoleInput(), new Tracker()).init();
    }
}