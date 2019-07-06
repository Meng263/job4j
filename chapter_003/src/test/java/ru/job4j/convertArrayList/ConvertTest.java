package ru.job4j.convertArrayList;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class ConvertTest {
    @Test
    public void when2ArrayThen() {
        ConvertList2Array list = new ConvertList2Array();
        List<Integer> expect = Arrays.asList(1, 2, 3, 4, 5, 6);
        List<int[]> result = Arrays.asList(new int[]{1, 2}, new int[]{3, 4, 5, 6});
        assertThat(list.convert(result), is(expect));
    }

    @Test
    public void when3ArrayThen() {
        ConvertList2Array list = new ConvertList2Array();
        List<Integer> expect = Arrays.asList(1, 2, 3, 4, 5, 6, 7);
        List<int[]> result = Arrays.asList(new int[]{1, 2}, new int[]{3, 4, 5, 6}, new int[]{7});
        assertThat(list.convert(result), is(expect));
    }
}
