package ru.job4j.sql.importer;

import org.junit.Assert;
import org.junit.Test;
import ru.job4j.sql.trackersql.ConnectionUtil;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.List;
import java.util.Properties;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;


public class ImportDBTest {
    private Connection getConnection() {
        try (InputStream in = ImportDB.class.getClassLoader().getResourceAsStream("app.properties")) {
            Properties config = new Properties();
            config.load(in);
            Connection connection = DriverManager.getConnection(
                    config.getProperty("url"),
                    config.getProperty("username"),
                    config.getProperty("password"));
            return ConnectionUtil.createConnectionRollback(connection);
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }

    @Test
    public void checkConnection() {
        Connection connection = getConnection();
        Assert.assertNotNull(connection);
    }

    @Test
    public void loadedUsersShouldBeCorrectImportedToDb() throws Exception {
        Connection connection = getConnection();
        try (ImportDB importDB = new ImportDB(connection)) {
            FileParser parser = new FileParser("src/test/resources/dump.txt");
            List<UserDTO> expected = parser.parse();
            importDB.saveUsers(expected);
            List<UserDTO> result = importDB.getUsers();
            assertThat(result, is(expected));
        }
    }
}