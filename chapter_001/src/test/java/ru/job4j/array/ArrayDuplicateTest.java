package ru.job4j.array;

import org.junit.Test;

import static org.junit.Assert.assertThat;
import static org.hamcrest.collection.IsArrayContainingInAnyOrder.arrayContainingInAnyOrder;

public class ArrayDuplicateTest {
    @Test
    public void whenRemoveDuplicatesThenArrayWithoutDuplicate() {
       ArrayDuplicate arrDup = new ArrayDuplicate();
        String[] arr = new String[]{"Жора", "123", "Java", "123", "Java"};
        String[] result = new String[]{"Жора", "Java", "123"};
        assertThat(result, arrayContainingInAnyOrder(arrDup.remove(arr)));
    }

    @Test
    public void whenRemoveDuplicatesThenArrayWithoutDuplicateSecondT() {
       ArrayDuplicate arrDup = new ArrayDuplicate();
        String[] arr = new String[]{"Жора", "123", "Java", "123", "Java", "123", "Java"};
        String[] result = new String[]{"123", "Java", "Жора"};
        assertThat(result, arrayContainingInAnyOrder(arrDup.remove(arr)));
    }
}