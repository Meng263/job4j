package ru.job4j.sort;

import org.junit.Test;

import java.util.*;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class SortUserTest {
    @Test
    public void whenShakeAddThan() {
        SortUser sortUser = new SortUser();
        User jora = new User(30, "Jora");
        User oleg = new User(23, "Oleg");
        User lena = new User(32, "Lena");
        List<User> list = List.of(
                jora,
                oleg,
                lena
        );
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
        User jora = new User(30, "Jora");
        User aleksey = new User(23, "Aleksey");
        User yulia = new User(32, "Yulia");
        List<User> list = List.of(
                jora,
                aleksey,
                yulia
        );
        List<User> result = sortUser.sortNameLength(list);
        List<User> expected = List.of(
                jora,
                yulia,
                aleksey
        );
        assertThat(result, is(expected));
    }

    @Test
    public void whenSortListByAllFieldsThan() {
        SortUser sortUser = new SortUser();
        User jora = new User(30, "Jora");
        User aleksey = new User(23, "Aleksey");
        User yuliaOne = new User(32, "Yulia");
        User yuliaTwo = new User(31, "Yulia");
        List<User> list = List.of(
                jora,
                yuliaTwo,
                aleksey,
                yuliaOne
        );
        List<User> result = sortUser.sortByAllFields(list);
        List<User> expected = List.of(
                aleksey,
                jora,
                yuliaTwo,
                yuliaOne
        );
        assertThat(result, is(expected));
    }
}