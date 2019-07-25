package ru.job4j.department;

import org.junit.Test;
import ru.job4j.depatrment.SortDepartment;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class SortDepartmentTest {

    @Test
    public void whenSortNaturalOrder() {
        String[] array = new String[7];
        array[0] = "K1\\SK1";
        array[1] = "K2\\SK1\\SSK1";
        array[2] = "K2\\SK1\\SSK2";
        array[3] = "K1\\SK1\\SSK1";
        array[4] = "K2";
        array[5] = "K1\\SK2";
        array[6] = "K1\\SK1\\SSK2";
        SortDepartment sortD = new SortDepartment();
        String[] expected = new String[9];
        expected[0] = "K1";
        expected[1] = "K1\\SK1";
        expected[2] = "K1\\SK1\\SSK1";
        expected[3] = "K1\\SK1\\SSK2";
        expected[4] = "K1\\SK2";
        expected[5] = "K2";
        expected[6] = "K2\\SK1";
        expected[7] = "K2\\SK1\\SSK1";
        expected[8] = "K2\\SK1\\SSK2";
        assertThat(expected, is(sortD.sort(array)));
    }

    @Test
    public void whenSortReverseOrder() {
        String[] array = new String[7];
        array[0] = "K1\\SK1";
        array[1] = "K2\\SK1\\SSK1";
        array[2] = "K2\\SK1\\SSK2";
        array[3] = "K1\\SK1\\SSK1";
        array[4] = "K2";
        array[5] = "K1\\SK2";
        array[6] = "K1\\SK1\\SSK2";
        SortDepartment sortD = new SortDepartment();
        String[] expected = new String[9];
        expected[0] = "K2";
        expected[1] = "K2\\SK1";
        expected[2] = "K2\\SK1\\SSK2";
        expected[3] = "K2\\SK1\\SSK1";
        expected[4] = "K1";
        expected[5] = "K1\\SK2";
        expected[6] = "K1\\SK1";
        expected[7] = "K1\\SK1\\SSK2";
        expected[8] = "K1\\SK1\\SSK1";
        assertThat(expected, is(sortD.sortReverse(array)));
    }
}
