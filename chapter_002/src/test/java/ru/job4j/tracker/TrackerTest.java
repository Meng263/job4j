package ru.job4j.tracker;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class TrackerTest {
    @Test
    public void whenAddNewItemThenTrackerHasSameItem() {
        Tracker tracker = new Tracker();
        long created = System.currentTimeMillis();
        Item item = new Item("test1", "testDescription", created);
        tracker.add(item);
        Item result = tracker.findById(item.getId());
        assertThat(result.getName(), is(item.getName()));
    }

    @Test
    public void whenReplaceNameThenReturnNewName() {
        Tracker tracker = new Tracker();
        Item previous = new Item("test1", "testDescription", 123L);
        // Добавляем заявку в трекер. Теперь в объект проинициализирован id.
        tracker.add(previous);
        // Создаем новую заявку.
        Item next = new Item("test2", "testDescription2", 1234L);
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
        Item first = new Item("jora", "testDesk1", 123);
        Item second = new Item("goro", "testDesk2", 124);
        Item third = new Item("gelo", "testDesk3", 126);
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
        Item first = new Item("Jora", "testdesk1", 123);
        Item second = new Item("Goro", "testdesk2", 124);
        tracker.add(first);
        tracker.add(second);
        String thirdId = tracker.add(new Item("Gelo", "test", 122)).getId();
        String firstId = first.getId();
        String secondId = second.getId();
        assertThat(0, is(tracker.indexOf(firstId)));
        assertThat(1, is(tracker.indexOf(secondId)));
        assertThat(2, is(tracker.indexOf(thirdId)));
    }

    @Test
    public void findAll() {
        Tracker old = new Tracker();
        Item first = new Item("jora", "testDesk1", 123);
        Item second = new Item("goro", "testDesk2", 124);
        Item third = new Item("gelo", "testDesk3", 126);
        old.add(first);
        old.add(second);
        old.add(third);
        Item[] result = old.findAll();
        Item[] expected = new Item[3];
        expected[0] = first;
        expected[1] = second;
        expected[2] = third;
        assertThat(expected.length, is(result.length));
        assertThat(expected[0].getId(), is(result[0].getId()));
        assertThat(expected[1].getId(), is(result[1].getId()));
        assertThat(expected[2].getId(), is(result[2].getId()));

    }

    @Test
    public void findByName() {
        Tracker old = new Tracker();
        Item first = new Item("jora", "testDesk1", 123);
        Item second = new Item("goro", "testDesk2", 124);
        Item third = new Item("goro", "testDesk3", 126);
        old.add(first);
        old.add(second);
        old.add(third);
        assertThat(old.findByName("goro")[0].getId(), is(second.getId()));
        assertThat(old.findByName("goro")[1].getId(), is(third.getId()));
    }
}