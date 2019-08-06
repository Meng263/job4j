package ru.job4j.streamarray;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class ArrayTest {
    @Test
    public void whenFilterArrayByEven() {
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
        int[] expected = new int[]{
                0,
                60,
                4,
                10
        };
        assertThat(expected, is(array.filterEven(mas)));
    }
    @Test
    public void whenSquareNumbers() {
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
        int[] expected = new int[]{
                0,
                3600,
                1,
                25,
                16,
                100,
                1681,
                441
        };
        assertThat(expected, is(array.squareOfNumbers(mas)));
    }

    @Test
    public void whenSumOfNumbers() {
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
        int expected = 140;
        assertThat(expected, is(array.sumOfNumbers((mas))));
    }
}
