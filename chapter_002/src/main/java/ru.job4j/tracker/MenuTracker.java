package ru.job4j.tracker;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class MenuTracker {
    private final Consumer<String> output;
    /**
     * @param хранит ссылку на объект .
     */
    private Input input;
    /**
     * @param хранит ссылку на объект .
     */
    private Tracker tracker;
    /**
     * @param хранит ссылку на массив типа UserAction.
     */
    private List<UserAction> actions = new ArrayList<>();

    /**
     * Конструктор.
     *
     * @param input   объект типа Input
     * @param tracker объект типа Tracker
     */
    public MenuTracker(Input input, Tracker tracker, Consumer<String> output) {
        this.input = input;
        this.tracker = tracker;
        this.output = output;
    }

    /**
     * Метод для получения массива меню.
     *
     * @return длину массива
     */
    public int getActionsLentgh() {
        return this.actions.size();
    }

    /**
     * Метод заполняет массив.
     */
    public void fillActions(StartUI ui) {
        this.actions.add(new AddItem(0, "Add new Item"));
        this.actions.add(new ShowItems(1, "Show all items"));
        this.actions.add(new EditItem(2, "Edit item"));
        this.actions.add(new DeleteItem(3, "Delete item"));
        this.actions.add(new FindItemById(4, "Find item by Id"));
        this.actions.add(new FindItemsByName(5, "Find items by name"));
        this.actions.add(new Exit(6, "Exit Program", ui));
    }

    /**
     * Метод в зависимости от указанного ключа, выполняет соотвествующие действие.
     *
     * @param key ключ операции
     */
    public void select(int key) {
        this.actions.get(key).execute(this.input, this.tracker);
    }

    /**
     * Метод выводит на экран меню.
     */
    public void show() {
        for (UserAction action : this.actions) {
            if (action != null) {
                output.accept(action.info());
            }
        }
    }

    /**
     * Класс реализует добавление новой заявки в хранилище
     */
    public class AddItem extends BaseAction {
        public AddItem(int key, String info) {
            super(key, info);
        }

        /**
         * Метод добавляет заявку в трекер
         *
         * @param input   объект типа input
         * @param tracker объект типа tracker
         */
        @Override
        public void execute(Input input, Tracker tracker) {
            output.accept("------------ Adding a new ticket --------------");
            String name = input.ask("Please, enter the ticket Name :");
            String desc = input.ask("Please, enter the ticket Description :");
            Item item = new Item(name, desc, System.currentTimeMillis());
            tracker.add(item);
            output.accept("--------- New ticket Id is: " + item.getId() + "-----------");
            output.accept("");
        }
    }

    /**
     * Класс реализует вывод всех заявок
     */
    public class ShowItems extends BaseAction {
        public ShowItems(int key, String info) {
            super(key, info);
        }

        @Override
        /**
         * Метод реализует вывод всех заявок в консоль
         */
        public void execute(Input input, Tracker tracker) {
            for (Item i : tracker.findAll()) {
                i.show();
            }
            output.accept("---------- done -----------");
            output.accept("");
        }
    }

    public class EditItem extends BaseAction {
        public EditItem(int key, String info) {
            super(key, info);
        }

        @Override
        /**
         * Метод редактирует заявку
         */
        public void execute(Input input, Tracker tracker) {
            output.accept("------------ edit ticket --------------");
            String id = input.ask("Please, enter the ticket ID to edit: ");
            String name = input.ask("Please, enter the ticket Name to edit: ");
            String desc = input.ask("Please, enter the ticket Description to edit: ");
            Item item = new Item(name, desc, System.currentTimeMillis());
            if (tracker.replace(id, item)) {
                output.accept("------------ ticket edited --------------");
                System.out.println();
            } else {
                output.accept("---- ticket edited error, try again ----");
                output.accept("");
            }
        }
    }

    public class DeleteItem extends BaseAction {
        public DeleteItem(int key, String info) {
            super(key, info);
        }

        @Override
        /**
         * Метод удаляет заявку
         */
        public void execute(Input input, Tracker tracker) {
            output.accept("------------ delete ticket --------------");
            String id = input.ask("Please, enter the ticket ID to delete: ");
            if (tracker.delete(id)) {
                output.accept("----------- ticket deleted-------------");
            } else {
                output.accept("------- ticket deleted error ----------");
            }
            output.accept("");
        }
    }

    public class FindItemById extends BaseAction {
        public FindItemById(int key, String info) {
            super(key, info);
        }

        @Override
        /**
         * Метод находит заявку по id и выводит ее в консоль
         */
        public void execute(Input input, Tracker tracker) {
            output.accept("---------- find tickets by ID ------------");
            String id = input.ask("Please, enter the ticket ID to search: ");
            if (tracker.findById(id) != null) {
                tracker.findById(id).show();
                output.accept("---------- search completed -------------");
            } else {
                output.accept("---------- item not found -------------");
                output.accept("---------- search completed -----------");
            }
            output.accept("");
        }

    }

    public class FindItemsByName extends BaseAction {
        public FindItemsByName(int key, String info) {
            super(key, info);
        }

        @Override
        /**
         * Метод находит заявки по имени и выводит их в консоль
         */
        public void execute(Input input, Tracker tracker) {
            output.accept("---------- find tickets by Name ------------");
            String name = input.ask("Please, enter the ticket Name to search: ");

            List<Item> items = tracker.findByName(name);
            for (Item i : items) {
                i.show();
            }
            output.accept("---------- search completed -------------");
            output.accept("");
        }

    }

    public class Exit extends BaseAction {
        public Exit(int key, String info, StartUI ui) {
            super(key, info);
            this.ui = ui;
        }

        private final StartUI ui;

        @Override
        /**
         * Метод реализует выход из программы
         */
        public void execute(Input input, Tracker tracker) {
            this.ui.switchToExt();
        }
    }
}
