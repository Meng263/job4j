package ru.job4j.tracker;

import org.junit.Test;
import ru.job4j.tracker.MenuTracker.DeleteItem;
import ru.job4j.tracker.MenuTracker.FindItemById;
import ru.job4j.tracker.MenuTracker.FindItemsByName;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.function.Consumer;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class MenuTrackerTest {
    @Test
    public void deleteActionTest() {
        Input input = mock(Input.class);
        Tracker tracker = new Tracker();
        List<String> outs = new ArrayList<>();
        Consumer<String> stubOut = outs::add;

        String itemName = "test item";
        tracker.add(new Item(itemName, "item desc", new Date()));
        int id = tracker.findByName(itemName).get(0).getId();

        DeleteItem deleteItem = new MenuTracker(input, tracker, stubOut).new DeleteItem(3, "Delete");

        when(input.ask(any(String.class))).thenReturn("3");
        when(input.ask(any(String.class))).thenReturn(String.valueOf(id));

        deleteItem.execute(input, tracker);
        assertEquals(
                outs.toString(),
                List.of(
                        "------------ delete ticket --------------",
                        "----------- ticket deleted-------------, "
                ).toString()
        );
    }

    @Test
    public void findByIdActionTest() {
        Input input = mock(Input.class);
        Tracker tracker = new Tracker();
        List<String> outs = new ArrayList<>();
        Consumer<String> stubOut = outs::add;

        String itemName = "test item";
        tracker.add(new Item(itemName, "item desc", new Date()));
        int id = tracker.findByName(itemName).get(0).getId();

        FindItemById findById = new MenuTracker(input, tracker, stubOut).new FindItemById(4, "Find by id");

        when(input.ask(any(String.class))).thenReturn("4");
        when(input.ask(any(String.class))).thenReturn(String.valueOf(id));

        findById.execute(input, tracker);
        assertEquals(
                outs.toString(),
                List.of(
                        "---------- find tickets by ID ------------",
                        "---------- search completed -------------, "
                ).toString()
        );
    }

    @Test
    public void findByNameActionTest() {
        Input input = mock(Input.class);
        Tracker tracker = new Tracker();
        List<String> outs = new ArrayList<>();
        Consumer<String> stubOut = outs::add;

        String itemName = "test item";
        tracker.add(new Item(itemName, "item desc", new Date()));
        int id = tracker.findByName(itemName).get(0).getId();

        FindItemsByName findByName = new MenuTracker(input, tracker, stubOut).new FindItemsByName(5, "Find by name");

        when(input.ask(any(String.class))).thenReturn("5");
        when(input.ask(any(String.class))).thenReturn(String.valueOf(id));

        findByName.execute(input, tracker);
        assertEquals(
                outs.toString(),
                List.of(
                        "---------- find tickets by Name ------------",
                        "---------- search completed -------------, ").toString()
        );
    }
}
