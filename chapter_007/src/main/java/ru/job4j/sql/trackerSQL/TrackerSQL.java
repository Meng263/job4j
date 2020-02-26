package ru.job4j.sql.trackerSQL;

import log.UsageLog4j2;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.job4j.tracker.ITracker;
import ru.job4j.tracker.Item;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

/**
 * Реалзация трекера заявок с хранением их в базе SQL
 */
public class TrackerSQL implements ITracker, AutoCloseable {
    private static final Logger LOG = LogManager.getLogger(UsageLog4j2.class.getName());

    private Connection connection;

    public TrackerSQL() {
        init();
        createTable();
    }

    public boolean init() {
        try (InputStream in = TrackerSQL.class.getClassLoader().getResourceAsStream("app.properties")) {
            Properties config = new Properties();
            config.load(in);
            Class.forName(config.getProperty("driver-class-name"));
            this.connection = DriverManager.getConnection(
                    config.getProperty("url"),
                    config.getProperty("username"),
                    config.getProperty("password")
            );
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
        return this.connection != null;
    }

    private void createTable() {
        try (var statement = connection.createStatement()) {
            statement.execute("create table if not exists items (" +
                    "id serial primary key, " +
                    "name VARCHAR(256), " +
                    "description VARCHAR(256), " +
                    "time TIMESTAMP);");
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
    }

    @Override
    public Item add(Item item) {
        Item result = null;
        try (var preparedStatement = connection
                .prepareStatement("insert into items (\"name\", description, \"time\") VALUES(?, ?, ?)")) {
            preparedStatement.setString(1, item.getName());
            preparedStatement.setString(2, item.getDecs());
            preparedStatement.setTimestamp(3, Timestamp.from(new Date(item.getTime()).toInstant()));
            preparedStatement.execute();
            result = item;
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
        return result;
    }

    @Override
    public boolean replace(String id, Item item) {
        var result = false;
        try (var preparedStatement = connection
                .prepareStatement("update items set name = ?, description = ?, time = ? where id = ?")) {
            preparedStatement.setString(1, item.getName());
            preparedStatement.setString(2, item.getDecs());
            preparedStatement.setTimestamp(3, Timestamp.from(new Date(item.getTime()).toInstant()));
            preparedStatement.setInt(4, Integer.parseInt(id));
            result = preparedStatement.execute();
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
        return result;
    }

    @Override
    public boolean delete(String id) {
        var result = false;
        try (var preparedStatement = connection.prepareStatement("delete from items where id = ?")) {
            preparedStatement.setInt(1, Integer.parseInt(id));
            result = preparedStatement.execute();
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
        return result;
    }

    @Override
    public List<Item> findAll() {
        List<Item> result = new ArrayList<>();
        try (var statement = connection.createStatement()) {
            var resultSet = statement.executeQuery("select * from items");
            while (resultSet.next()) {
                Item item = new Item(resultSet.getString("name"),
                        resultSet.getString("description"),
                        resultSet.getTimestamp("time").getTime());
                item.setId(String.valueOf(resultSet.getInt("id")));
                result.add(item);
            }
            resultSet.close();
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
        return result;
    }

    @Override
    public List<Item> findByName(String key) {
        List<Item> result = new ArrayList<>();
        try (var preparedStatement = connection.prepareStatement("select * from items where name = ?")) {
            preparedStatement.setString(1, key);
            var resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Item item = new Item(resultSet.getString("name"),
                        resultSet.getString("description"),
                        resultSet.getTimestamp("time").getTime());
                item.setId(String.valueOf(resultSet.getInt("id")));
                result.add(item);
            }
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
        return result;
    }

    @Override
    public Item findById(String id) {
        Item result = null;
        try (var preparedStatement = connection.prepareStatement("select * from items where id = ?")) {
            preparedStatement.setInt(1, Integer.parseInt(id));
            var resultSet = preparedStatement.executeQuery();
            resultSet.next();
            Item item = new Item(resultSet.getString("name"),
                    resultSet.getString("description"),
                    resultSet.getTimestamp("time").getTime());
            item.setId(String.valueOf(resultSet.getInt("id")));
            result = item;
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
        return result;
    }

    public boolean dropAll() {
        boolean result = false;
        try (var statement = connection.createStatement()) {
            result = statement.execute("drop table items");
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
        return result;
    }

    @Override
    public void close() throws SQLException {
        if (connection != null) {
            connection.close();
        }
    }
}