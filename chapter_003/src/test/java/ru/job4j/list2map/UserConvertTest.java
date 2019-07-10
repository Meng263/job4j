package ru.job4j.list2map;

import org.junit.Test;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class UserConvertTest {
    @Test
    public void when3ItemThan() {
        UserConvert userConvert = new UserConvert();
        List<User> list = new LinkedList<>();
        User jora = new User(133, "Jora", "Yekaterinburg");
        User oleg = new User(122, "Oleg", "Moscow");
        User lena = new User(144, "Lena", "Paris");
        list.add(jora);
        list.add(oleg);
        list.add(lena);
        HashMap<Integer, User> result = userConvert.process(list);
        HashMap<Integer, User> expected = new HashMap<>();
        expected.put(133, jora);
        expected.put(122, oleg);
        expected.put(144, lena);
        assertThat(result, is(expected));
    }

    @Test
    public void when2ItemThan() {
        UserConvert userConvert = new UserConvert();
        List<User> list = new LinkedList<>();
        User jora = new User(13, "Jora", "Yekaterinburg");
        User lena = new User(144, "Lena", "Paris");
        list.add(jora);
        list.add(lena);
        HashMap<Integer, User> result = userConvert.process(list);
        HashMap<Integer, User> expected = new HashMap<>();
        expected.put(13, jora);
        expected.put(144, lena);
        assertThat(result, is(expected));
    }
}
