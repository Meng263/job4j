package ru.job4j.tracker;


/**
 * @version $Id$
 * @since 0.1
 */
public class StartUI {
    /**
     * Константа меню для добавления новой заявки.
     */
    private static final String ADD = "0";
    /**
     * Консатнта меню для отображения всех заявок
     */
    private static final String SHOW = "1";
    /**
     * Константа меню для редактирования заявки
     */
    private static final String EDIT = "2";
    /**
     * Константа меню для удаления заявки
     */
    private static final String DELETE = "3";
    /**
     * Константа меню для поиска заявок по id
     */
    private static final String FINDID = "4";
    /**
     * Константа меню для поиска заявок по имени
     */
    private static final String FINDNAME = "5";
    /**
     * Константа для выхода из цикла.
     */
    private static final String EXIT = "6";

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
        boolean exit = false;
        while (!exit) {
            this.showMenu();
            String answer = this.input.ask("Select: ");
            switch (answer) {
                case ADD:
                    this.createItem();
                    break;
                case SHOW:
                    this.showItems();
                    break;
                case EDIT:
                    this.editItem();
                    break;
                case DELETE:
                    this.deleteItem();
                    break;
                case FINDID:
                    this.findById();
                    break;
                case FINDNAME:
                    this.findByName();
                    break;
                case EXIT:
                    exit = true;
                    break;
                default:
                    System.out.println("invalid input");
            }
        }
    }

    /**
     * Метод реализует вывод всех заявок в консоль
     */
    private void showItems() {
        for (Item i : this.tracker.findAll()) {
            showItem(i);
        }
        System.out.println("---------- done -----------");
        System.out.println();
    }

    /**
     * Метод редактирует заявку
     *
     * @return true в случае, если заявка отредактирована
     */
    private boolean editItem() {
        boolean result = false;
        System.out.println("------------ edit ticket --------------");
        String id = this.input.ask("Please, enter the ticket ID to edit: ");
        String name = this.input.ask("Please, enter the ticket Name to edit: ");
        String desc = this.input.ask("Please, enter the ticket Description to edit: ");
        Item item = new Item(name, desc, System.currentTimeMillis());
        if (this.tracker.replace(id, item)) {
            System.out.println("------------ ticket edited --------------");
            System.out.println();
            result = true;
        } else {
            System.out.println("---- ticket edited error, try again ----");
            System.out.println();
        }
        return result;
    }

    /**
     * Метод удаляет заявку
     *
     * @return true, если заявку удалось удалить
     */
    private boolean deleteItem() {
        boolean result = false;
        System.out.println("------------ delete ticket --------------");
        String id = this.input.ask("Please, enter the ticket ID to delete: ");
        if (this.tracker.delete(id)) {
            System.out.println("----------- ticket deleted-------------");
            System.out.println();
            result = true;
        } else {
            System.out.println("------- ticket deleted error ----------");
            System.out.println();
        }
        return result;
    }

    /**
     * Метод выводит в консоль заявку по ее ID
     */
    private void findById() {
        System.out.println("---------- find tickets by ID ------------");
        String id = this.input.ask("Please, enter the ticket ID to search: ");
        if (this.tracker.findById(id) != null) {
            showItem(this.tracker.findById(id));
            System.out.println("---------- search completed -------------");
            System.out.println();
        } else {
            System.out.println("---------- item not found -------------");
            System.out.println("---------- search completed -----------");
            System.out.println();
        }
    }

    /**
     * Метод выводит в консоль заявку по ее Name
     */
    private void findByName() {
        System.out.println("---------- find tickets by Name ------------");
        String name = this.input.ask("Please, enter the ticket Name to search: ");
        Item[] items = this.tracker.findByName(name);
        for (Item i : items) {
            showItem(i);
        }
        System.out.println("---------- search completed -------------");
        System.out.println();
    }

    /**
     * Метод реализует добавленяи новый заявки в хранилище.
     */
    private void createItem() {
        System.out.println("------------ Adding a new ticket --------------");
        String name = this.input.ask("Please, enter the ticket Name :");
        String desc = this.input.ask("Please, enter the ticket Description :");
        Item item = new Item(name, desc, System.currentTimeMillis());
        this.tracker.add(item);
        System.out.println("--------- New ticket Id is: " + item.getId() + "-----------");
        System.out.println();
    }

    private void showMenu() {
        System.out.println("0. Add new Item");
        System.out.println("1. Show all items");
        System.out.println("2. Edit item");
        System.out.println("3. Delete item");
        System.out.println("4. Find item by Id");
        System.out.println("5. Find items by name");
        System.out.println("6. Exit Program");
    }

    private void showItem(Item item) {
        System.out.println("------------ ticket --------------");
        System.out.println("Item id " + item.getId());
        System.out.println("Item name " + item.getName());
        System.out.println("Item description " + item.getDecs());
        System.out.println();
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