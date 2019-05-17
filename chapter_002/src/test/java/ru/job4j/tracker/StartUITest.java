package ru.job4j.tracker;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.After;
import org.junit.Before;

public class StartUITest {
    @Test
    public void whenUserAddItemThenTrackerHasNewItemWithSameName() {
        Tracker tracker = new Tracker();     // создаём Tracker
        Input input = new StubInput(new String[]{"0", "test name", "desc", "6"});   //создаём StubInput с последовательностью действий
        new StartUI(input, tracker).init();     //   создаём StartUI и вызываем метод init()
        assertThat(tracker.findAll()[0].getName(), is("test name")); // проверяем, что нулевой элемент массива в трекере содержит имя, введённое при эмуляции.
    }

    @Test
    public void whenUpdateThenTrackerHasUpdatedValue() {
        // создаём Tracker
        Tracker tracker = new Tracker();
        //Напрямую добавляем заявку
        Item item = tracker.add(new Item("test name", "desc", System.currentTimeMillis()));
        //создаём StubInput с последовательностью действий(производим замену заявки)
        Input input = new StubInput(new String[]{"2", item.getId(), "test replace", "заменили заявку", "6"});
        // создаём StartUI и вызываем метод init()
        new StartUI(input, tracker).init();
        // проверяем, что нулевой элемент массива в трекере содержит имя, введённое при эмуляции.
        assertThat(tracker.findById(item.getId()).getName(), is("test replace"));
    }

    @Test
    public void whenUserDeleteItemThenTrackerHasItemZeroWithNameGelo() {
        Tracker tracker = new Tracker();     // создаём Tracker
        Item one = tracker.add(new Item("goro", "descOne", System.currentTimeMillis()));
        Item two = tracker.add(new Item("gelo", "descTwo", System.currentTimeMillis()));
        Input input = new StubInput(new String[]{"3", one.getId(), "6"});
        new StartUI(input, tracker).init();
        assertThat(tracker.findAll()[0].getName(), is("gelo"));
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
            .append("0. Add new Item\r\n")
            .append("1. Show all items\r\n")
            .append("2. Edit item\r\n")
            .append("3. Delete item\r\n")
            .append("4. Find item by Id\r\n")
            .append("5. Find items by name\r\n")
            .append("6. Exit Program\r\n")
            .toString();

    @Test
    public void whenShowMenuAndExit() {
        Tracker tracker = new Tracker();
        Input input = new StubInput(new String[]{"6"});
        new StartUI(input, tracker).init();
        assertThat(new String(out.toByteArray()), is(menu));
    }

    @Test
    public void whenShowAll() {
        Tracker tracker = new Tracker();
        Item one = tracker.add(new Item("goro", "descOne", System.currentTimeMillis()));
        Input input = new StubInput(new String[]{"1", "6"});
        new StartUI(input, tracker).init();
        assertThat(
                new String(out.toByteArray()),
                is(new StringBuilder()
                        .append(menu)
                        .append("------------ ticket --------------\r\n")
                        .append("Item id ")
                        .append(one.getId())
                        .append("\r\n")
                        .append("Item name ")
                        .append(one.getName())
                        .append("\r\n")
                        .append("Item description ")
                        .append(one.getDecs())
                        .append("\r\n\r\n")
                        .append("---------- done -----------\r\n\r\n")
                        .append(menu)
                        .toString()
                )
        );
    }

    @Test
    public void whenFindItemByName() {
        Tracker tracker = new Tracker();
        Item one = tracker.add(new Item("goro", "descOne", System.currentTimeMillis()));
        Item two = tracker.add(new Item("gelo", "descTwo", System.currentTimeMillis()));
        Item three = tracker.add(new Item("goro", "descthree", System.currentTimeMillis()));
        Input input = new StubInput(new String[]{"5", "goro", "6"});
        new StartUI(input, tracker).init();
        assertThat(
                new String(out.toByteArray()),
                is(new StringBuilder()
                        .append(menu)
                        .append("---------- find tickets by Name ------------\r\n")
                        .append("------------ ticket --------------\r\n")
                        .append("Item id ")
                        .append(one.getId())
                        .append("\r\n")
                        .append("Item name ")
                        .append(one.getName())
                        .append("\r\n")
                        .append("Item description ")
                        .append(one.getDecs())
                        .append("\r\n\r\n")
                        .append("------------ ticket --------------\r\n")
                        .append("Item id ")
                        .append(three.getId())
                        .append("\r\n")
                        .append("Item name ")
                        .append(three.getName())
                        .append("\r\n")
                        .append("Item description ")
                        .append(three.getDecs())
                        .append("\r\n\r\n")
                        .append("---------- search completed -------------\r\n\r\n")
                        .append(menu)
                        .toString()
                ));
    }

    @Test
    public void whenFindById() {
        Tracker tracker = new Tracker();
        Item one = tracker.add(new Item("goro", "descOne", System.currentTimeMillis()));
        Input input = new StubInput(new String[]{"4", one.getId(), "6"});
        Item two = tracker.add(new Item("gelo", "descTwo", System.currentTimeMillis()));
        Item three = tracker.add(new Item("goro", "descthree", System.currentTimeMillis()));
        new StartUI(input, tracker).init();
        assertThat(
                new String(out.toByteArray()),
                is(new StringBuilder()
                        .append(menu)
                        .append("---------- find tickets by ID ------------\r\n")
                        .append("------------ ticket --------------\r\n")
                        .append("Item id ")
                        .append(one.getId())
                        .append("\r\n")
                        .append("Item name ")
                        .append(one.getName())
                        .append("\r\n")
                        .append("Item description ")
                        .append(one.getDecs())
                        .append("\r\n\r\n")
                        .append("---------- search completed -------------\r\n\r\n")
                        .append(menu)
                        .toString()
                )
        );

    }
}
