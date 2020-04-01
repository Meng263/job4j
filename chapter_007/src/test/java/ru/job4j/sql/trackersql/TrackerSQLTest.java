package ru.job4j.sql.trackersql;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ru.job4j.tracker.Item;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class TrackerSQLTest {

    private List<Item> items = new ArrayList<>();

    public TrackerSQLTest() {
        items.add(new Item("fix printer", "need to fix very quickly", 123L));
        items.add(new Item("add mouse", "need to add mouse to computer", 1333L));
        items.add(new Item("replace toner", "need to change cartrige in printer", 122342L));
        items.add(new Item("fix printer", "need to fix very quickly", 1753L));
    }

    @Before
    public void addItems() {
        try (TrackerSQL sql = new TrackerSQL()) {
            items.forEach(sql::add);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @After
    public void dropAll() {
        try (TrackerSQL sql = new TrackerSQL()) {
            sql.dropAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void checkConnection() {
        try (TrackerSQL sql = new TrackerSQL()) {
            assertThat(sql.init(), is(true));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void whenFindAll() {
        try (TrackerSQL sql = new TrackerSQL()) {
            List<Item> result = sql.findAll();
            assertThat(result, is(items));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void whenAddItemThanAdded() {
        try (TrackerSQL sql = new TrackerSQL()) {
            sql.init();
            var item = new Item("fix printer", "need to fix very quickly", 5641238454L);
            item.setId("5");
            sql.add(item);
            Item result = sql.findById("5");
            assertThat(result, is(item));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void whenFindByName() {
        try (TrackerSQL sql = new TrackerSQL()) {
           List<Item> result = sql.findByName("add mouse");
           Item item = items.get(1);
           List<Item> expected = List.of(item);
           assertThat(result, is(expected));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void whenFindById() {
        try (TrackerSQL sql = new TrackerSQL()) {
            Item result = sql.findById("2");
            Item item = items.get(1);
            assertThat(result, is(item));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void whenDeleteItemThanDeleted() {
        try (TrackerSQL sql = new TrackerSQL()) {
            sql.delete("1");
            items.remove(0);
            List<Item> result = sql.findAll();
            assertThat(result, is(items));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void whenReplaceThanReplaced() {
        try (TrackerSQL sql = new TrackerSQL()) {
            Item item = new Item("replased", "desc for replased", 1245L);
            item.setId("1");
            sql.replace("1", item);
            List<Item> result = sql.findAll();
            items.set(0, item);
            result.sort(Comparator.comparing(Item::getId));
            items.sort(Comparator.comparing(Item::getId));
            assertThat(result, is(items));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
