package ru.job4j.tracker;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;


public class ValidateInputTest {
    private final ByteArrayOutputStream mem = new ByteArrayOutputStream();
    private final PrintStream out = System.out;

    @Before
    public void loadMem() {
        System.setOut(new PrintStream(this.mem));
    }

    @After
    public void loadSys() {
        System.setOut(this.out);
    }

    @Test
    public void whenInvalidInput() {
        ValidateInput input = new ValidateInput(
                new StubInput(new String[]{"invalid", "6"})
        );
        input.ask("Enter", new int[]{0, 1, 2, 3, 4, 5, 6});
        assertThat(
                this.mem.toString(),
                is(
                        new StringBuilder().append("Illegal input")
                                .append(System.lineSeparator())
                                .append("Please, try again")
                                .append(System.lineSeparator())
                                .toString()
                )
        );
    }

    @Test
    public void whenOutOfRangeInput() {
        ValidateInput input = new ValidateInput(
                new StubInput(new String[]{"-1", "6"})
        );
        input.ask("Enter", new int[]{0, 1, 2, 3, 4, 5, 6});
        assertThat(
                this.mem.toString(),
                is(
                        new StringBuilder().append("Out of range")
                                .append(System.lineSeparator())
                                .append("Please, try again")
                                .append(System.lineSeparator())
                                .toString()
                )
        );
    }
}
