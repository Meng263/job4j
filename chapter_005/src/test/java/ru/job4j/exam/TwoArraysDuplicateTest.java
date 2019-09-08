package ru.job4j.exam;

import org.junit.Test;
import static org.junit.Assert.*;
import static org.hamcrest.Matchers.is;

public class TwoArraysDuplicateTest {
    @Test
    public void whenTwoArraysHasTwoDuplicateThanReturnTwoElement() {
        TwoArraysDuplicate tad = new TwoArraysDuplicate();
        Integer[] left = new Integer[]{1, 2, 3, 0};
        Integer[] right = new Integer[]{0, 4, 10, 2, 45, 33};
        Integer[] expected = new Integer[]{0, 2};
        assertThat(expected, is(tad.checkDuplicate(left, right)));
    }

    @Test
    public void whenTwoArraysHasNoDuplicateThanReturnEmptyArray() {
        TwoArraysDuplicate tad = new TwoArraysDuplicate();
        Integer[] left = new Integer[]{1, 2, 3, 0};
        Integer[] right = new Integer[]{6, 4, 10, 20, 45, 33};
        Integer[] expected = new Integer[]{};
        assertThat(expected, is(tad.checkDuplicate(left, right)));
    }


}