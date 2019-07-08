package ru.job4j.sortedSet;

import org.junit.Test;

import java.util.*;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class SortUserTest {
    @Test
    public void whenShakeAdd() {
        SortUser sortUser = new SortUser();
        List<User> list = new ArrayList<>();
        User jora = new User(30, "Jora");
        User oleg = new User(23, "Oleg");
        User lena = new User(32, "Lena");
        list.add(jora);
        list.add(oleg);
        list.add(lena);

        Set<User> result = sortUser.sort(list);
        Set<User> expected = new LinkedHashSet<>();
        expected.add(oleg);
        expected.add(jora);
        expected.add(lena);
        assertThat(result, is(expected));

    }
}