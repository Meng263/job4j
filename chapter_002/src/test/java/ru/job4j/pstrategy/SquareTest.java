package ru.job4j.pstrategy;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class SquareTest {

    @Test
    public void whenSquarePaintThenSquare() {
        Square square = new Square();
        String expected = square.draw();
        StringBuilder result = new StringBuilder();
        result.append("@@@@@@\n");
        result.append("@    @\n");
        result.append("@    @\n");
        result.append("@    @\n");
        result.append("@    @\n");
        result.append("@@@@@@\n");
        assertThat(result.toString(), is(expected));
    }
}
