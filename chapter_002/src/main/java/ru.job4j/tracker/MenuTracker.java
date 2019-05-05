package ru.job4j.tracker;

import java.util.ArrayList;
import java.util.List;

public class MenuTracker {
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
    public MenuTracker(Input input, Tracker tracker) {
        this.input = input;
        this.tracker = tracker;
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
    public void fillActions() {
        this.actions.add(new AddItem());
        this.actions.add(new ShowItems());
        this.actions.add(new EditItem());
        this.actions.add(new DeleteItem());
        this.actions.add(new FindItemById());
        this.actions.add(new FindItemsByName());
        this.actions.add(new Exit());
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
                System.out.println(action.info());
            }
        }
    }

    /**
     * Класс реализует добавление новой заявки в хранилище
     */
    public class AddItem implements UserAction {
        /**
         * Метод возвращает код операции
         *
         * @return ключ
         */
        @Override
        public int key() {
            return 0;
        }

        /**
         * Метод возвращает информацию о операщии
         *
         * @return инфо
         */
        @Override
        public String info() {
            return String.format("%s. %s", key(), "Add new Item");
        }

        /**
         * Метод добавляет заявку в трекер
         *
         * @param input   объект типа input
         * @param tracker объект типа tracker
         */
        @Override
        public void execute(Input input, Tracker tracker) {
            System.out.println("------------ Adding a new ticket --------------");
            String name = input.ask("Please, enter the ticket Name :");
            String desc = input.ask("Please, enter the ticket Description :");
            Item item = new Item(name, desc, System.currentTimeMillis());
            tracker.add(item);
            System.out.println("--------- New ticket Id is: " + item.getId() + "-----------");
            System.out.println();
        }
    }

    /**
     * Класс реализует вывод всех заявок
     */
    public class ShowItems implements UserAction {
        /**
         * Метод возвращает код операции
         *
         * @return ключ
         */
        @Override
        public int key() {
            return 1;
        }

        /**
         * Метод возвращает информацию о операщии
         *
         * @return инфо
         */
        @Override
        public String info() {
            return String.format("%s. %s", key(), "Show all items");
        }

        @Override
        /**
         * Метод реализует вывод всех заявок в консоль
         */
        public void execute(Input input, Tracker tracker) {
            for (Item i : tracker.findAll()) {
                i.show();
            }
            System.out.println("---------- done -----------");
            System.out.println();
        }
    }

    public class EditItem implements UserAction {
        /**
         * Метод возвращает код операции
         *
         * @return ключ
         */
        @Override
        public int key() {
            return 2;
        }

        /**
         * Метод возвращает информацию о операщии
         *
         * @return инфо
         */
        @Override
        public String info() {
            return String.format("%s. %s", key(), "Edit item");
        }

        @Override
        /**
         * Метод редактирует заявку
         */
        public void execute(Input input, Tracker tracker) {
            System.out.println("------------ edit ticket --------------");
            String id = input.ask("Please, enter the ticket ID to edit: ");
            String name = input.ask("Please, enter the ticket Name to edit: ");
            String desc = input.ask("Please, enter the ticket Description to edit: ");
            Item item = new Item(name, desc, System.currentTimeMillis());
            if (tracker.replace(id, item)) {
                System.out.println("------------ ticket edited --------------");
                System.out.println();
            } else {
                System.out.println("---- ticket edited error, try again ----");
                System.out.println();
            }
        }
    }

    public class DeleteItem implements UserAction {
        /**
         * Метод возвращает код операции
         *
         * @return ключ
         */
        @Override
        public int key() {
            return 3;
        }

        /**
         * Метод возвращает информацию о операщии
         *
         * @return инфо
         */
        @Override
        public String info() {
            return String.format("%s. %s", key(), "Delete item");
        }

        @Override
        /**
         * Метод удаляет заявку
         */
        public void execute(Input input, Tracker tracker) {
            System.out.println("------------ delete ticket --------------");
            String id = input.ask("Please, enter the ticket ID to delete: ");
            if (tracker.delete(id)) {
                System.out.println("----------- ticket deleted-------------");
                System.out.println();
            } else {
                System.out.println("------- ticket deleted error ----------");
                System.out.println();
            }
        }
    }

    public class FindItemById implements UserAction {
        /**
         * Метод возвращает код операции
         *
         * @return ключ
         */
        @Override
        public int key() {
            return 4;
        }

        /**
         * Метод возвращает информацию о операщии
         *
         * @return инфо
         */
        @Override
        public String info() {
            return String.format("%s. %s", key(), "Find item by Id");
        }

        @Override
        /**
         * Метод находит заявку по id и выводит ее в консоль
         */
        public void execute(Input input, Tracker tracker) {
            System.out.println("---------- find tickets by ID ------------");
            String id = input.ask("Please, enter the ticket ID to search: ");
            if (tracker.findById(id) != null) {
                tracker.findById(id).show();
                System.out.println("---------- search completed -------------");
                System.out.println();
            } else {
                System.out.println("---------- item not found -------------");
                System.out.println("---------- search completed -----------");
                System.out.println();
            }
        }

    }

    public class FindItemsByName implements UserAction {
        /**
         * Метод возвращает код операции
         *
         * @return ключ
         */
        @Override
        public int key() {
            return 5;
        }

        /**
         * Метод возвращает информацию о операщии
         *
         * @return инфо
         */
        @Override
        public String info() {
            return String.format("%s. %s", key(), "Find items by name");
        }

        @Override
        /**
         * Метод находит заявки по имени и выводит их в консоль
         */
        public void execute(Input input, Tracker tracker) {
            System.out.println("---------- find tickets by Name ------------");
            String name = input.ask("Please, enter the ticket Name to search: ");
            Item[] items = tracker.findByName(name);
            for (Item i : items) {
                i.show();
            }
            System.out.println("---------- search completed -------------");
            System.out.println();
        }

    }

    public class Exit implements UserAction {
        /**
         * Метод возвращает код операции
         *
         * @return ключ
         */
        @Override
        public int key() {
            return 6;
        }

        /**
         * Метод возвращает информацию о операщии
         *
         * @return инфо
         */
        @Override
        public String info() {
            return String.format("%s. %s", key(), "Exit Program");
        }

        @Override
        /**
         * Метод находит заявки по имени и выводит их в консоль
         */
        public void execute(Input input, Tracker tracker) {

        }
    }
}
