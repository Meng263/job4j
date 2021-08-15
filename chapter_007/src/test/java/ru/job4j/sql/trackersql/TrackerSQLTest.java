package ru.job4j.sql.trackersql;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import ru.job4j.tracker.Item;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.*;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class TrackerSQLTest {

    private final List<Item> items = new ArrayList<>();

    public TrackerSQLTest() {
    }

    private Connection init() {
        try (InputStream in = TrackerSQL.class.getClassLoader().getResourceAsStream("app.properties")) {
            Properties config = new Properties();
            config.load(in);
            Class.forName(config.getProperty("driver-class-name"));
            return DriverManager.getConnection(
                    config.getProperty("url"),
                    config.getProperty("username"),
                    config.getProperty("password"));
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }

    @Before
    public void initItems() {
        items.clear();
        items.add(new Item("fix printer", "need to fix very quickly", new Date()));
        items.add(new Item("add mouse", "need to add mouse to computer", new Date()));
        items.add(new Item("replace toner", "need to change cartrige in printer", new Date()));
        items.add(new Item("fix printer", "need to fix very quickly", new Date()));
    }

    @Test
    public void checkConnection() {
        try (TrackerSQL sql = new TrackerSQL(ConnectionUtil.createConnectionRollback(this.init()))) {
            Assert.assertNotNull(sql.getConnection());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void whenFindAll() {
        try (TrackerSQL sql = new TrackerSQL(ConnectionUtil.createConnectionRollback(this.init()))) {
            items.forEach(sql::add);
            List<Item> result = sql.findAll();
            assertThat(result, is(items));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void whenAddItemThanAdded() {
        try (TrackerSQL sql = new TrackerSQL(ConnectionUtil.createConnectionRollback(this.init()))) {
            items.forEach(sql::add);
            var item = new Item("fix printer", "need to fix very quickly", new Date());
            item = sql.add(item);
            Item result = sql.findById(item.getId());
            assertThat(result, is(item));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void whenFindByName() {
        try (TrackerSQL sql = new TrackerSQL(ConnectionUtil.createConnectionRollback(this.init()))) {
            items.forEach(sql::add);
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
        try (TrackerSQL sql = new TrackerSQL(ConnectionUtil.createConnectionRollback(this.init()))) {
            items.forEach(sql::add);
            Item item = items.get(1);
            Item result = sql.findById(item.getId());
            assertThat(result, is(item));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void whenDeleteItemThanDeleted() {
        try (TrackerSQL sql = new TrackerSQL(ConnectionUtil.createConnectionRollback(this.init()))) {
            items.forEach(sql::add);
            sql.delete(sql.findAll().get(0).getId());
            items.remove(0);
            List<Item> result = sql.findAll();
            result.sort(Comparator.comparing(Item::getId));
            assertThat(result, is(items));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void whenReplaceThanReplaced() {
        try (TrackerSQL sql = new TrackerSQL(ConnectionUtil.createConnectionRollback(this.init()))) {
            items.forEach(sql::add);
            Item itemToReplace = new Item("replased", "desc for replased", new Date(1245L));
            int idToReplace = sql.findAll().get(0).getId();
            sql.replace(idToReplace, itemToReplace);
            itemToReplace.setId(idToReplace);
            List<Item> result = sql.findAll();
            items.set(0, itemToReplace);
            result.sort(Comparator.comparing(Item::getId));
            items.sort(Comparator.comparing(Item::getId));
            assertThat(result, is(items));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
