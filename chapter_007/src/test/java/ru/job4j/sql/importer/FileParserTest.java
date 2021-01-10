package ru.job4j.sql.importer;

import org.junit.Test;

import java.io.IOException;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class FileParserTest {
    @Test
    public void testFileShouldBeParsedWell() throws IOException {
        FileParser parser = new FileParser("src/test/resources/dump.txt");
            List<UserDTO> users = parser.parse();
            List<UserDTO> expected = List.of(
                    new UserDTO("Petr Arsentev", "parsentev@yandex.ru"),
                    new UserDTO("Ivan Ivanov", "iivanov@gmail.com"),
                    new UserDTO("Oleg Sivakov", "Meng@bk.ru"),
                    new UserDTO("Andrey Radzievskii", "RadzievskiiAndrei@mail.ru"),
                    new UserDTO("Goro Combatov", "goro@gmail.com"),
                    new UserDTO("Sub Zero", "sub_zero@yahoo.com")
            );

            assertThat(users, is(expected));
    }
}