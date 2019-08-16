package ru.job4j.generic;

import org.junit.Before;
import org.junit.Test;

import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class SimpleArrayTest {
    private SimpleArray<String> simpleArray;
    private Iterator<String> iterator;

    @Before
    public void setUp() {
        simpleArray = new SimpleArray<String>(3);
        simpleArray.add("5");
        simpleArray.add("3");
        simpleArray.add("2");
        iterator = simpleArray.iterator();
    }

    @Test(expected = NoSuchElementException.class)
    public void whenAddElemIntegerThenGetInteger() {
        SimpleArray<Integer> integerSimpleArray = new SimpleArray<Integer>(3);
        integerSimpleArray.add(5);
        integerSimpleArray.add(3);
        integerSimpleArray.add(2);
        assertThat(integerSimpleArray.get(0), is(5));
        assertThat(integerSimpleArray.get(1), is(3));
        assertThat(integerSimpleArray.get(2), is(2));
        integerSimpleArray.add(10);
    }

    @Test
    public void whenAddElemStringThenGetString() {
        assertThat(simpleArray.get(0), is("5"));
        assertThat(simpleArray.get(1), is("3"));
        assertThat(simpleArray.get(2), is("2"));
    }

    @Test
    public void whenRemoveElem() {
        simpleArray.remove(0);
        assertThat(simpleArray.get(0), is("3"));
        assertThat(simpleArray.get(1), is("2"));
    }

    @Test
    public void whenSetElem() {
        simpleArray.set(0, "123");
        assertThat(simpleArray.get(0), is("123"));
        assertThat(simpleArray.get(1), is("3"));
        assertThat(simpleArray.get(2), is("2"));
    }

    @Test(expected = NoSuchElementException.class)
    public void sequentialHasNextInvocationDoesntAffectRetrievalOrder() {
        assertThat(iterator.hasNext(), is(true));
        assertThat(iterator.hasNext(), is(true));
        assertThat(iterator.next(), is("5"));
        assertThat(iterator.next(), is("3"));
        assertThat(iterator.next(), is("2"));
        iterator.next();
    }

    @Test
    public void hasNextNextSequentialInvocation() {
        assertThat(iterator.hasNext(), is(true));
        assertThat(iterator.next(), is("5"));
        assertThat(iterator.hasNext(), is(true));
        assertThat(iterator.next(), is("3"));
        assertThat(iterator.hasNext(), is(true));
        assertThat(iterator.next(), is("2"));
        assertThat(iterator.hasNext(), is(false));
    }
}