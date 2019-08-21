package ru.job4j.list;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class CycleLinkedListTest {
    @Test
    public void whenListIsCycleThanTrue() {
        CycleLinkedList list = new CycleLinkedList();
        list.initCycle();
        assertThat(list.hasCycle(list.getFirst()), is(true));
    }

    @Test
    public void whenListIsNonCycleThanFalse() {
        CycleLinkedList list = new CycleLinkedList();
        list.initNonCycle();
        assertThat(list.hasCycle(list.getFirst()), is(false));
    }
}