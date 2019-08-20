package ru.job4j.list;

import static org.junit.Assert.*;


import org.junit.Test;
import org.junit.Before;

import java.util.ConcurrentModificationException;
import java.util.Iterator;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class DynamicLinkedContainerTest {
    DynamicLinkedContainer<String> strings;

    @Before
    public void setUp() {
        strings = new DynamicLinkedContainer<>();
        strings.add("first");
        strings.add("second");
        strings.add("third");
        strings.add("fourth");
        strings.add("fifth");
    }

    @Test ()
    public void whenAddFiveElementsThan() {
        assertThat(strings.get(0), is("first"));
        assertThat(strings.get(1), is("second"));
        assertThat(strings.get(2), is("third"));
        assertThat(strings.get(3), is("fourth"));
        assertThat(strings.get(4), is("fifth"));
    }

    @Test
    public void hasNextNextSequentialInvocation() {
        Iterator<String> iterator = strings.iterator();
        assertThat(iterator.hasNext(), is(true));
        assertThat(iterator.next(), is("first"));
        assertThat(iterator.hasNext(), is(true));
        assertThat(iterator.next(), is("second"));
        assertThat(iterator.hasNext(), is(true));
        assertThat(iterator.next(), is("third"));
        assertThat(iterator.hasNext(), is(true));
        assertThat(iterator.next(), is("fourth"));
        assertThat(iterator.hasNext(), is(true));
        assertThat(iterator.next(), is("fifth"));
        assertThat(iterator.hasNext(), is(false));
    }

    @Test (expected = ConcurrentModificationException.class)
    public void isFailFastThrowConcurrentModificationException() {
        Iterator<String> iterator = strings.iterator();
        strings.add("elem");
        iterator.hasNext();
    }
}