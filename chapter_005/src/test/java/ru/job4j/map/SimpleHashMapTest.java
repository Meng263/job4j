package ru.job4j.map;

import org.junit.Test;
import org.junit.Before;

import java.util.Iterator;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class SimpleHashMapTest {
    SimpleHashMap<Integer, String> map = new SimpleHashMap<>();
    Iterator<String> iterator;

    @Before
    public void setUp() {
        map.insert(1, "Первый");
        map.insert(2, "Второй");
        map.insert(3, "Третий");
        iterator = map.iterator();
    }

    @Test
    public void whenGetElementsByKeyThan() {
        assertThat(map.get(1), is("Первый"));
        assertThat(map.get(2), is("Второй"));
        assertThat(map.get(3), is("Третий"));
    }

    @Test
    public void whenReplaceValueByKeyThan() {
        assertThat(map.insert(3, "Бывший третий, теперь четвертый"), is(true));
        assertThat(map.get(3), is("Бывший третий, теперь четвертый"));
    }

    @Test
    public void whenDeleteElemByKeyThanGetNull() {
        assertThat(map.delete(1), is(true));
        assertThat(map.get(1) == null, is(true));
        assertThat(map.delete(1), is(false));
    }

    @Test
    public void whenWorkWithNullKeyThan() {
        assertThat(map.insert(null, "null"), is(true));
        assertThat(map.insert(null, "null!!!"), is(true));
        assertThat(map.get(null), is("null!!!"));
        assertThat(map.delete(null), is(true));
        assertThat(map.get(null) == null, is(true));
    }

    @Test
    public void whenSizeEqualsMoreThanThresholdThenGrowTheTable() {
        map.insert(4, "четвертый");
        map.insert(4040, "четыре сорок");
        map.insert(5, "пятый");
        map.insert(6, "шестой");
        map.insert(7, "седьмой");
        map.insert(8, "восьмой");
        map.insert(9, "девятый");
        map.insert(10, "десятый");
        assertThat(map.get(1), is("Первый"));
        assertThat(map.get(2), is("Второй"));
        assertThat(map.get(3), is("Третий"));
        assertThat(map.get(4), is("четвертый"));
        assertThat(map.get(5), is("пятый"));
        assertThat(map.get(6), is("шестой"));
        assertThat(map.get(7), is("седьмой"));
        assertThat(map.get(8), is("восьмой"));
        assertThat(map.get(9), is("девятый"));
        assertThat(map.get(10), is("десятый"));
        assertThat(map.get(1), is("Первый"));
        assertThat(map.get(2), is("Второй"));
        assertThat(map.get(3), is("Третий"));
        assertThat(map.get(4040), is("четыре сорок"));
    }

    @Test
    public void hasNextNextSequentialInvocation() {
        assertThat(iterator.hasNext(), is(true));
        assertThat(iterator.hasNext(), is(true));
        assertThat(iterator.next(), is("Первый"));
        assertThat(iterator.hasNext(), is(true));
        assertThat(iterator.next(), is("Второй"));
        assertThat(iterator.hasNext(), is(true));
        assertThat(iterator.next(), is("Третий"));
        assertThat(iterator.hasNext(), is(false));
    }
}