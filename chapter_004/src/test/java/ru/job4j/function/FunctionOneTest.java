package ru.job4j.function;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class FunctionOneTest {
    @Test
    public void whenLinearFunctionThenLinearResults() {
        List<Double> result = FunctionOne.diapason(5, 8, x -> 2 * x + 1);
        List<Double> expected = Arrays.asList(11D, 13D, 15D);
        assertThat(result, is(expected));
    }

    @Test
    public void whenSqrtFunctionThenLinearResults() {
        List<Double> result = FunctionOne.diapason(5, 8, x -> x * x + 1);
        List<Double> expected = Arrays.asList(26D, 37D, 50D);
        assertThat(result, is(expected));
    }

    @Test
    public void whenLogFunctionThenLinearResults() {
        List<Double> result = FunctionOne.diapason(5, 8, x -> Math.log(x) + 1);
        List<Double> expected = Arrays.asList(Math.log(5) + 1D, Math.log(6) + 1D, Math.log(7) + 1D);
        assertThat(result, is(expected));
    }



}
