package ru.job4j.list;

import org.junit.Test;
import org.junit.Before;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class SimpleQueueTest {
    private SimpleQueue<Integer> queue;

    @Before
    public void beforeTest() {
        queue = new SimpleQueue<>();
        queue.push(1);
        queue.push(2);
        queue.push(3);
    }

    @Test
    public void whenPollAllElements() {
        assertThat(queue.size(), is(3));
        assertThat(queue.poll(), is(1));
        assertThat(queue.poll(), is(2));
        assertThat(queue.poll(), is(3));
        assertThat(queue.poll() == null, is(true));
    }

    @Test
    public void whenPollOneElementsPushOneAndPollOne() {
        assertThat(queue.poll(), is(1));
        queue.push(4);
        assertThat(queue.poll(), is(2));
        assertThat(queue.poll(), is(3));
        queue.push(5);
        assertThat(queue.poll(), is(4));
        assertThat(queue.poll(), is(5));
        assertThat(queue.isEmpty(), is(true));
    }
}