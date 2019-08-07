package ru.job4j.streamarray;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class ArrayTest {
    @Test
    public void whenDoStreamArray() {
        int[] mas = new int[]{
                0,
                60,
                -1,
                5,
                4,
                10,
                41,
                21
        };
        Array array = new Array();
        assertThat(3716, is(array.doStreamArray(mas)));
    }
}
