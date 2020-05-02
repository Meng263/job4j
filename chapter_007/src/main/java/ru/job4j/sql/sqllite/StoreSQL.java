package ru.job4j.sql.sqllite;

import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Data manipulation
 */
public class StoreSQL implements AutoCloseable {
    private final Config config;
    private Connection connect;

    public StoreSQL(Config config) {
        this.config = config;
        connectToDB();
    }

    private void connectToDB() {
        String url = config.get("url");
        try {
            Connection conn = DriverManager.getConnection(url);
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

    /**
     * Put to base n records
     * @param size records amount
     */
    public void generate(int size) {
        createTableIfNotExist();
        if (!load().isEmpty()) {
            deleteAllRows();
        }

        try (Statement insertStatement = connect.createStatement()) {
            connect.setAutoCommit(false);
            for (int i = 0; i < size; i++) {
                insertStatement.addBatch(String.format("INSERT INTO store VALUES (%d); ", i));
            }
            insertStatement.executeBatch();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void deleteAllRows() {
        String sql = "DELETE FROM store";
        try (PreparedStatement insertStatement = connect.prepareStatement(sql)) {
            insertStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void createTableIfNotExist() {
        try (PreparedStatement createTableStatement = connect.prepareStatement(
                "CREATE TABLE IF NOT EXISTS store(field INTEGER);")) {
            createTableStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void dropTable() {
        try (var statement = connect.createStatement()) {
            statement.execute("DROP TABLE store");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * get add records from db
     * @return List of records
     */
    public List<Entry> load() {
        List<Entry> list = new ArrayList<>(100);
        try (Statement statement = connect.createStatement()) {
            var resultSet = statement.executeQuery("SELECT * FROM store");
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