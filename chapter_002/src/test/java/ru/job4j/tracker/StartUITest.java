package ru.job4j.tracker;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Date;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class StartUITest {
    @Test
    public void whenUserAddItemThenTrackerHasNewItemWithSameName() {
        Tracker tracker = new Tracker();     // создаём Tracker
        Input input = new StubInput(new String[]{"0", "test name", "desc", "6"});   //создаём StubInput с последовательностью действий
        new StartUI(input, tracker, System.out::println).init();     //   создаём StartUI и вызываем метод init()
        assertThat(tracker.findAll().get(0).getName(), is("test name")); // проверяем, что нулевой элемент массива в трекере содержит имя, введённое при эмуляции.
    }

    @Test
    public void whenUpdateThenTrackerHasUpdatedValue() {
        // создаём Tracker
        Tracker tracker = new Tracker();
        //Напрямую добавляем заявку
        Item item = tracker.add(new Item("test name", "desc", new Date()));
        //создаём StubInput с последовательностью действий(производим замену заявки)
        Input input = new StubInput(new String[]{"2", String.valueOf(item.getId()), "test replace", "заменили заявку", "6"});
        // создаём StartUI и вызываем метод init()
        new StartUI(input, tracker, System.out::println).init();
        // проверяем, что нулевой элемент массива в трекере содержит имя, введённое при эмуляции.
        assertThat(tracker.findById(item.getId()).getName(), is("test replace"));
    }

    @Test
    public void whenUserDeleteItemThenTrackerHasItemZeroWithNameGelo() {
        Tracker tracker = new Tracker();     // создаём Tracker
        Item one = tracker.add(new Item("goro", "descOne", new Date()));
        Item two = tracker.add(new Item("gelo", "descTwo", new Date()));
        Input input = new StubInput(new String[]{"3", String.valueOf(one.getId()), "6"});
        new StartUI(input, tracker, System.out::println).init();
        assertThat(tracker.findAll().get(0).getName(), is("gelo"));
    }

    ByteArrayOutputStream out = new ByteArrayOutputStream();
    PrintStream stdout = System.out;

    @Before
    public void loadOutput() {
        System.setOut(new PrintStream(this.out));
    }

    @After
    public void backOutput() {
        System.setOut(this.stdout);
    }

    private final String menu = new StringBuilder()
            .append("0. Add new Item")
            .append(System.lineSeparator())
            .append("1. Show all items")
            .append(System.lineSeparator())
            .append("2. Edit item")
            .append(System.lineSeparator())
            .append("3. Delete item")
            .append(System.lineSeparator())
            .append("4. Find item by Id")
            .append(System.lineSeparator())
            .append("5. Find items by name")
            .append(System.lineSeparator())
            .append("6. Exit Program")
            .append(System.lineSeparator())
            .toString();

    @Test
    public void whenShowMenuAndExit() {
        Tracker tracker = new Tracker();
        Input input = new StubInput(new String[]{"6"});
        new StartUI(input, tracker, System.out::println).init();
        assertThat(new String(out.toByteArray()), is(menu));
    }

    @Test
    public void whenShowAll() {
        Tracker tracker = new Tracker();
        Item one = tracker.add(new Item("goro", "descOne", new Date()));
        Input input = new StubInput(new String[]{"1", "6"});
        new StartUI(input, tracker, System.out::println).init();
        assertThat(
                new String(out.toByteArray()),
                is(new StringBuilder()
                        .append(menu)
                        .append("------------ ticket --------------")
                        .append(System.lineSeparator())
                        .append("Item id ")
                        .append(one.getId())
                        .append(System.lineSeparator())
                        .append("Item name ")
                        .append(one.getName())
                        .append(System.lineSeparator())
                        .append("Item description ")
                        .append(one.getDescription())
                        .append(System.lineSeparator())
                        .append(System.lineSeparator())
                        .append("---------- done -----------")
                        .append(System.lineSeparator())
                        .append(System.lineSeparator())
                        .append(menu)
                        .toString()
                )
        );
    }

    @Test
    public void whenFindItemByName() {
        Tracker tracker = new Tracker();
        Item one = tracker.add(new Item("goro", "descOne", new Date()));
        Item two = tracker.add(new Item("gelo", "descTwo", new Date()));
        Item three = tracker.add(new Item("goro", "descthree", new Date()));
        Input input = new StubInput(new String[]{"5", "goro", "6"});
        new StartUI(input, tracker, System.out::println).init();
        assertThat(
                new String(out.toByteArray()),
                is(new StringBuilder()
                        .append(menu)
                        .append("---------- find tickets by Name ------------")
                        .append(System.lineSeparator())
                        .append("------------ ticket --------------")
                        .append(System.lineSeparator())
                        .append("Item id ")
                        .append(one.getId())
                        .append(System.lineSeparator())
                        .append("Item name ")
                        .append(one.getName())
                        .append(System.lineSeparator())
                        .append("Item description ")
                        .append(one.getDescription())
                        .append(System.lineSeparator())
                        .append(System.lineSeparator())
                        .append("------------ ticket --------------")
                        .append(System.lineSeparator())
                        .append("Item id ")
                        .append(three.getId())
                        .append(System.lineSeparator())
                        .append("Item name ")
                        .append(three.getName())
                        .append(System.lineSeparator())
                        .append("Item description ")
                        .append(three.getDescription())
                        .append(System.lineSeparator())
                        .append(System.lineSeparator())
                        .append("---------- search completed -------------")
                        .append(System.lineSeparator())
                        .append(System.lineSeparator())
                        .append(menu)
                        .toString()
                ));
    }

    @Test
    public void whenFindById() {
        Tracker tracker = new Tracker();
        Item one = tracker.add(new Item("goro", "descOne", new Date()));
        Input input = new StubInput(new String[]{"4", String.valueOf(one.getId()), "6"});
        Item two = tracker.add(new Item("gelo", "descTwo", new Date()));
        Item three = tracker.add(new Item("goro", "descthree", new Date()));
        new StartUI(input, tracker, System.out::println).init();
        assertThat(
                new String(out.toByteArray()),
                is(new StringBuilder()
                        .append(menu)
                        .append("---------- find tickets by ID ------------")
                        .append(System.lineSeparator())
                        .append("------------ ticket --------------")
                        .append(System.lineSeparator())
                        .append("Item id ")
                        .append(one.getId())
                        .append(System.lineSeparator())
                        .append("Item name ")
                        .append(one.getName())
                        .append(System.lineSeparator())
                        .append("Item description ")
                        .append(one.getDescription())
                        .append(System.lineSeparator())
                        .append(System.lineSeparator())
                        .append("---------- search completed -------------")
                        .append(System.lineSeparator())
                        .append(System.lineSeparator())
                        .append(menu)
                        .toString()
                )
        );

    }
}
