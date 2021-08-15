package ru.job4j.tracker;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class TrackerTest {
    @Test
    public void whenAddNewItemThenTrackerHasSameItem() {
        Tracker tracker = new Tracker();
        Date created = new Date();
        Item item = new Item("test1", "testDescription", created);
        tracker.add(item);
        Item result = tracker.findById(item.getId());
        assertThat(result.getName(), is(item.getName()));
    }

    @Test
    public void whenReplaceNameThenReturnNewName() {
        Tracker tracker = new Tracker();
        Item previous = new Item("test1", "testDescription", new Date());
        // Добавляем заявку в трекер. Теперь в объект проинициализирован id.
        tracker.add(previous);
        // Создаем новую заявку.
        Item next = new Item("test2", "testDescription2", new Date());
        // Проставляем старый id из previous, который был сгенерирован выше.
        next.setId(previous.getId());
        // Обновляем заявку в трекере.
        tracker.replace(previous.getId(), next);
        // Проверяем, что заявка с таким id имеет новые имя test2.
        assertThat(tracker.findById(previous.getId()).getName(), is("test2"));
    }

    @Test
    public void whenDeleteItemThenDeleted() {
        Tracker old = new Tracker();
        Item first = new Item("jora", "testDesk1", new Date());
        Item second = new Item("goro", "testDesk2", new Date());
        Item third = new Item("gelo", "testDesk3", new Date());
        old.add(first);
        old.add(second);
        old.add(third);
        old.delete(second.getId());
        assertThat(old.indexOf(third.getId()), is(1));
        assertThat(old.indexOf(first.getId()), is(0));
    }

    @Test
    public void indexOf() {
        Tracker tracker = new Tracker();
        Item first = new Item("Jora", "testdesk1", new Date());
        Item second = new Item("Goro", "testdesk2", new Date());
        tracker.add(first);
        tracker.add(second);
        int thirdId = tracker.add(new Item("Gelo", "test", new Date())).getId();
        int firstId = first.getId();
        int secondId = second.getId();
        assertThat(0, is(tracker.indexOf(firstId)));
        assertThat(1, is(tracker.indexOf(secondId)));
        assertThat(2, is(tracker.indexOf(thirdId)));
    }

    @Test
    public void findAll() {
        Tracker old = new Tracker();
        Item first = new Item("jora", "testDesk1", new Date());
        Item second = new Item("goro", "testDesk2", new Date());
        Item third = new Item("gelo", "testDesk3", new Date());
        old.add(first);
        old.add(second);
        old.add(third);
        List<Item> result = old.findAll();
        List<Item> expected = new ArrayList<>();
        expected.add(first);
        expected.add(second);
        expected.add(third);
        assertThat(expected.size(), is(result.size()));
        assertThat(expected, is(result));
    }

    @Test
    public void findByName() {
        Tracker old = new Tracker();
        Item first = new Item("jora", "testDesk1", new Date());
        Item second = new Item("goro", "testDesk2", new Date());
        Item third = new Item("goro", "testDesk3", new Date());
        old.add(first);
        old.add(second);
        old.add(third);
        assertThat(old.findByName("goro").get(0).getId(), is(second.getId()));
        assertThat(old.findByName("goro").get(1).getId(), is(third.getId()));
    }
}