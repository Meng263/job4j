package ru.job4j.sql.importer;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FileParser {
    private String path;

    public FileParser(String path) {
        this.path = path;
    }

    public List<UserDTO> parse() {
        List<UserDTO> users = new ArrayList<>();
        try (BufferedReader rd = new BufferedReader(new FileReader(path))) {
            rd.lines()
                    .map(s -> {
                        String[] split = s.split(";");
                        return new UserDTO(split[0], split[1]);
                    })
                    .forEach(users::add);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return users;
    }
}
