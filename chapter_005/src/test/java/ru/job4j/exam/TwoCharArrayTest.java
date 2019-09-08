package ru.job4j.exam;

import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.Matchers.is;

public class TwoCharArrayTest {
    @Test
    public void whenTwoArrayHasEqualsElementsThanTrue() {
        TwoCharArray tca = new TwoCharArray();
        char[] left = "0123456".toCharArray();
        char[] right = "6543210".toCharArray();
        assertThat(tca.checkCharArrays(left, right), is(true));
    }
    @Test
    public void whenTwoArrayHasNonEqualsElementsThanFalse() {
        TwoCharArray tca = new TwoCharArray();
        char[] left = "0123456".toCharArray();
        char[] right = "6549210".toCharArray();
        assertThat(tca.checkCharArrays(left, right), is(false));
    }
}