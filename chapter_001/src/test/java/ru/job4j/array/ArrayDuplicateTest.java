package ru.job4j.array;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class ArrayDuplicateTest {
    @Test
    public void whenRemoveDuplicatesThenArrayWithoutDuplicate() {
       ArrayDuplicate arrDup = new ArrayDuplicate();
        String[] arr = new String[]{"Жора", "123", "Java", "123", "Java"};
        String[] result = new String[]{"Жора", "Java", "123"};
        assertThat(result, is(arrDup.remove(arr)));
    }

    @Test
    public void whenRemoveDuplicatesThenArrayWithoutDuplicateSecondT() {
       ArrayDuplicate arrDup = new ArrayDuplicate();
        String[] arr = new String[]{"Жора", "123", "Java", "123", "Java", "123", "Java"};
        String[] result = new String[]{"Жора", "Java", "123"};
        assertThat(result, is(arrDup.remove(arr)));
    }
}