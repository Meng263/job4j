package ru.job4j.io;

import java.util.HashMap;
import java.util.Map;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.StringJoiner;

/**
 * Чтение файла конфигурации
 */
public class Config {
    private final String path;
    private final Map<String, String> values = new HashMap<>();

    public Config(final String path) {
        this.path = path;
    }

    /**
     * Метод загружает строки из файла в map, ключ - строка до разделителя "=",
     * занчение после разделителя, коментарии игнорируются
     * Пример: "hibernate.connection.username=postgres" запишется в карту как:
     * ключ "hibernate.connection.username", значение "postgres"
     */
    public void load() {
        try (BufferedReader read = new BufferedReader(new FileReader(this.path))) {
            read.lines().forEach(line -> {
                if (!line.isEmpty()
                        && !line.startsWith("/*")
                        && !line.startsWith("*")
                        && !line.startsWith("#")
                        && !line.startsWith(" ")) {
                    String[] keyValueArr = line.split("=");
                    if (keyValueArr.length == 2) {
                        values.put(keyValueArr[0], keyValueArr[1]);
                    }
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public String value(String key) {
        return this.values.get(key);
    }

    @Override
    public String toString() {
        StringJoiner out = new StringJoiner(System.lineSeparator());
        try (BufferedReader read = new BufferedReader(new FileReader(this.path))) {
            read.lines().forEach(out::add);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return out.toString();
    }

    public static void main(String[] args) {
        System.out.println(new Config("app.properties"));
    }
}