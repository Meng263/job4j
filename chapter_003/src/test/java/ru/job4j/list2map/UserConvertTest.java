package ru.job4j.list2map;

import org.junit.Test;
import java.util.List;
import java.util.Map;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class UserConvertTest {
    @Test
    public void when3ItemThan() {
        UserConvert userConvert = new UserConvert();
        User jora = new User(133, "Jora", "Yekaterinburg");
        User oleg = new User(122, "Oleg", "Moscow");
        User lena = new User(144, "Lena", "Paris");
        List<User> list = List.of(
                jora,
                oleg,
                lena
        );
        Map<Integer, User> result = userConvert.process(list);
        Map<Integer, User> expected = Map.of(
                133, jora,
                122, oleg,
                144, lena
        );
        assertThat(result, is(expected));
    }

    @Test
    public void when2ItemThan() {
        UserConvert userConvert = new UserConvert();
        User jora = new User(13, "Jora", "Yekaterinburg");
        User lena = new User(144, "Lena", "Paris");
        List<User> list = List.of(
                jora,
                lena
        );
        Map<Integer, User> result = userConvert.process(list);
        Map<Integer, User> expected = Map.of(
                13, jora,
                144, lena
        );
        assertThat(result, is(expected));
    }
}
