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
        int[] range = new int[]{0, 1, 2, 3, 4, 5, 6};
        MenuTracker menu = new MenuTracker(this.input, this.tracker);
        menu.fillActions();
        int ext = 0;
        do {
            menu.show();
            ext = input.ask("Select:", range);
            menu.select(ext);
        } while (ext != 6);
    }
    /**
     * Запускт программы.
     *
     * @param args
     */
    public static void main(String[] args) {
        new StartUI(new ValidateInput( new ConsoleInput()), new Tracker()).init();
    }
}