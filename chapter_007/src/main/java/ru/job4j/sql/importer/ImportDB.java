package ru.job4j.sql.importer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ImportDB implements AutoCloseable {
    private final Connection connection;

    public ImportDB(Connection connection) {
        this.connection = connection;
    }

    public void saveUsers(List<UserDTO> users) {
        try (PreparedStatement ps = connection
                .prepareStatement("create  table if not exists users(id serial primary key, name text, email text);")) {
            ps.execute();
        } catch (
                SQLException e) {
            e.printStackTrace();
        }
        for (
                UserDTO user : users) {
            try (PreparedStatement ps = connection.prepareStatement("insert into users (name, email) values (?, ?);")) {
                ps.setString(1, user.getName());
                ps.setString(2, user.getEmail());
                ps.execute();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public List<UserDTO> getUsers() {
        List<UserDTO> users = new ArrayList<>();
        try (PreparedStatement ps = connection.prepareStatement("select * from users;")) {
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {
                users.add(new UserDTO(resultSet.getString("name"), resultSet.getString("email")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    @Override
    public void close() throws Exception {
        if (connection != null) {
            connection.close();
        }
    }
}
