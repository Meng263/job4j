package ru.job4j.solid.dip.first;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DataDbExtractor {
    private Connection connection = DriverManager.getConnection("localhost:5432/postgres");

    public DataDbExtractor() throws SQLException {
    }


    public String getData() throws SQLException {
        ResultSet resultSet = connection.createStatement().executeQuery("select * from avto.data");
        return resultSet.getString(0);
    }

}
