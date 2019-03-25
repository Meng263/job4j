package ru.job4j.array;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class MergeArraysTest {
    @Test
    public void when2BiggerThan1() {
        MergeArrays arrays = new MergeArrays();
        int[] arrOne = {3, 4, 5, 6, 7};
        int[] arrTwo = {5, 6, 7, 8, 9, 10, 11, 12};

        int[] expect = {3, 4, 5, 5, 6, 6, 7, 7, 8, 9, 10, 11, 12};

        assertThat(arrays.merge(arrOne, arrTwo), is(expect));
    }

    @Test
    public void when1BiggerThan2() {
        MergeArrays arrays = new MergeArrays();
        int[] arrOne = {5, 6, 7, 8, 9, 10, 11, 12};
        int[] arrTwo = {3, 4, 5, 6, 7};

        int[] expect = {3, 4, 5, 5, 6, 6, 7, 7, 8, 9, 10, 11, 12};

        assertThat(arrays.merge(arrOne, arrTwo), is(expect));
    }

    @Test
    public void when1equally2() {
        MergeArrays arrays = new MergeArrays();
        int[] arrOne = {3, 4, 5, 6, 7};
        int[] arrTwo = {5, 6, 7, 8, 9};

        int[] expect = {3, 4, 5, 5, 6, 6, 7, 7, 8, 9};

        assertThat(arrays.merge(arrOne, arrTwo), is(expect));
    }
}