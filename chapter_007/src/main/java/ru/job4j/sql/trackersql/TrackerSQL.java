package ru.job4j.sql.trackersql;

import log.UsageLog4j2;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.job4j.tracker.ITracker;
import ru.job4j.tracker.Item;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Реализация трекера заявок с хранением их в базе SQL
 */
public class TrackerSQL implements ITracker, AutoCloseable {
    private static final Logger LOG = LogManager.getLogger(UsageLog4j2.class.getName());

    private Connection connection;

    public TrackerSQL(Connection connection) {
        this.connection = connection;
        createTable();
    }

    public Connection getConnection() {
        return connection;
    }

    private void createTable() {
        try (var statement = connection.createStatement()) {
            statement.execute("CREATE TABLE IF NOT EXISTS items ("
                    + "id SERIAL PRIMARY KEY, "
                    + "name VARCHAR(256), "
                    + "description VARCHAR(256), "
                    + "time TIMESTAMP);");
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
    }

    @Override
    public Item add(Item item) {
        Item result = null;
        final String sql = "INSERT INTO items (name, description, time) VALUES(?, ?, ?);";
        try (var preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, item.getName());
            preparedStatement.setString(2, item.getDescription());
            preparedStatement.setTimestamp(3, Timestamp.from(item.getTime().toInstant()));
            preparedStatement.executeUpdate();
            var resultSet = preparedStatement.getGeneratedKeys();
            if (resultSet.next()) {
                item.setId(resultSet.getInt(1));
                result = item;
            }
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
        return result;
    }

    @Override
    public boolean replace(int id, Item item) {
        var result = false;
        try (var preparedStatement = connection
                .prepareStatement("UPDATE items SET name = ?, description = ?, time = ? WHERE id = ?;")) {
            preparedStatement.setString(1, item.getName());
            preparedStatement.setString(2, item.getDescription());
            preparedStatement.setTimestamp(3, Timestamp.from(item.getTime().toInstant()));
            preparedStatement.setInt(4, id);
            result = preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
        return result;
    }

    @Override
    public boolean delete(int id) {
        var result = false;
        try (var preparedStatement = connection.prepareStatement("DELETE FROM items WHERE id = ?;")) {
            preparedStatement.setInt(1, id);
            result = preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
        return result;
    }

    @Override
    public List<Item> findAll() {
        List<Item> result = new ArrayList<>();
        try (var statement = connection.createStatement()) {
            var resultSet = statement.executeQuery("SELECT * FROM items;");
            while (resultSet.next()) {
                Item item = new Item(resultSet.getString("name"),
                        resultSet.getString("description"),
                        new Date(resultSet.getTimestamp("time").getTime()));
                item.setId(resultSet.getInt("id"));
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
        try (var preparedStatement = connection.prepareStatement("SELECT * FROM items WHERE name = ?;")) {
            preparedStatement.setString(1, key);
            var resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Item item = new Item(resultSet.getString("name"),
                        resultSet.getString("description"),
                        new Date(resultSet.getTimestamp("time").getTime()));
                item.setId(resultSet.getInt("id"));
                result.add(item);
            }
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
        return result;
    }

    @Override
    public Item findById(int id) {
        Item result = null;
        try (var preparedStatement = connection.prepareStatement("SELECT * FROM items WHERE id = ?;")) {
            preparedStatement.setInt(1, id);
            var resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                Item item = new Item(resultSet.getString("name"),
                        resultSet.getString("description"),
                        new Date(resultSet.getTimestamp("time").getTime()));
                item.setId(resultSet.getInt("id"));
                result = item;
            }
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
        return result;
    }

    public boolean dropAll() {
        boolean result = false;
        try (var statement = connection.createStatement()) {
            result = statement.executeUpdate("DROP TABLE items;") > 0;
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