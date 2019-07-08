package ru.job4j.sort;

import org.junit.Test;

import java.util.*;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class SortUserTest {
    @Test
    public void whenShakeAddthan() {
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

    @Test
    public void whenSortListByNameLengthThan() {
        SortUser sortUser = new SortUser();
        List<User> list = new ArrayList<>();
        User jora = new User(30, "Jora");
        User aleksey = new User(23, "Aleksey");
        User yulia = new User(32, "Yulia");
        list.add(jora);
        list.add(aleksey);
        list.add(yulia);
        List<User> result = sortUser.sortNameLength(list);
        List<User> expected = new ArrayList<>();
        expected.add(jora);
        expected.add(yulia);
        expected.add(aleksey);
        assertThat(result, is(expected));
    }

    @Test
    public void whenSortListByAllFieldsThan() {
        SortUser sortUser = new SortUser();
        List<User> list = new ArrayList<>();
        User jora = new User(30, "Jora");
        User aleksey = new User(23, "Aleksey");
        User yuliaOne = new User(32, "Yulia");
        User yuliaTwo = new User(31, "Yulia");
        list.add(jora);
        list.add(yuliaTwo);
        list.add(aleksey);
        list.add(yuliaOne);
        List<User> result = sortUser.sortByAllFields(list);
        List<User> expected = new ArrayList<>();
        expected.add(aleksey);
        expected.add(jora);
        expected.add(yuliaTwo);
        expected.add(yuliaOne);
        assertThat(result, is(expected));
    }
}