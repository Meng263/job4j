package ru.job4j.matrix;

import java.util.List;
import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class MatrixToListTest {
    @Test
    public void whenMatrixToList() {
        Integer[][] matrix = new Integer[][]{
                {1, 2},
                {3, 4}
        };
        List<Integer> expected = List.of(1, 2, 3, 4);
        MatrixToList mtl = new MatrixToList();
        assertThat(expected, is(mtl.convert(matrix)));
        }
}
