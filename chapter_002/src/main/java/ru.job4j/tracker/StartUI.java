package ru.job4j.tracker;


import java.util.function.Consumer;

/**
 * @version $Id$
 * @since 0.1
 */
public class StartUI {

    private final Consumer<String> output;
    /**
     * Получение данных от пользователя.
     */
    private final Input input;

    /**
     * Хранилище заявок.
     */
    private final Tracker tracker;

    /**
     * Переключатель, true выход из программы, false программа продолжает работать
     */
    private boolean ext = false;

    /**
     * Метод устанавливает значение ext true для выхода из программы
     */
    public void switchToExt() {
        ext = true;
    }

    /**
     * Конструтор инициализирующий поля.
     *
     * @param input   ввод данных.
     * @param tracker хранилище заявок.
     */
    public StartUI(Input input, Tracker tracker, Consumer<String> output) {
        this.input = input;
        this.tracker = tracker;
        this.output = output;
    }

    /**
     * Основой цикл программы.
     */
    public void init() {
        int[] range = new int[]{0, 1, 2, 3, 4, 5, 6};
        MenuTracker menu = new MenuTracker(this.input, this.tracker, output);
        menu.fillActions(this);
        int action = 0;
        do {
            menu.show();
            action = input.ask("Select:", range);
            menu.select(action);
        } while (!this.ext);
    }

    /**
     * Запуск программы.
     *
     * @param args
     */
    public static void main(String[] args) {
        new StartUI(new ValidateInput(new ConsoleInput()), new Tracker(), System.out::println).init();
    }
}