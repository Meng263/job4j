package ru.job4j.sql.sqllite;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StoreSQL implements AutoCloseable {
    private final Config config;
    private Connection connect;

    public StoreSQL(Config config) {
        this.config = config;
        connectToDB();
    }

    private void connectToDB() {
        String url = config.get("url");
        try (Connection conn = DriverManager.getConnection(url)) {
            if (conn != null) {
                DatabaseMetaData meta = conn.getMetaData();
                connect = conn;
                System.out.println("The driver name is " + meta.getDriverName());
                System.out.println("A new database has been created.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void generate(int size) {
        try {
            PreparedStatement createTableStatement = connect.prepareStatement(
                    "create table if not exists storage(" +
                            "id serial primary key," +
                            "field integer)");

            StringBuilder sql = new StringBuilder("insert into storage(field) values ");
            for (int i = 0; i < size - 1; i++) {
                sql.append(String.format(" (%d),", i));
            }
            sql.append(String.format(" (%d)", size));
            PreparedStatement insertStatement = connect.prepareStatement(sql.toString());

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean dropAll() {
        boolean result = false;
        try (var statement = connect.createStatement()) {
            result = statement.execute("drop table items");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public List<Entry> load() {
        List<Entry> list = new ArrayList<>(100);
        try (Statement statement = connect.createStatement()) {
            var resultSet = statement.executeQuery("select * from storage");
            while (resultSet.next()) {
                list.add(new Entry(resultSet.getInt("field")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public void close() throws Exception {
        if (connect != null) {
            connect.close();
        }
    }
}