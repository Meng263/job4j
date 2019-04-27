package ru.job4j.pstrategy;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class TriangleTest {
    @Test
    public void whenTriangleDrawThenTriangle() {
        Triangle triangle = new Triangle();
        String expected = triangle.draw();
        StringBuilder result = new StringBuilder();
        result.append("@    \n");
        result.append("@@@  \n");
        result.append("@@@@ \n");
        result.append("@@@@@@\n");
        assertThat(result.toString(), is(expected));
    }
}
