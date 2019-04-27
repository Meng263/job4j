package ru.job4j.pstrategy;

import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.After;
import org.junit.Before;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class PaintTest {
    ByteArrayOutputStream out = new ByteArrayOutputStream();
    PrintStream stdout = System.out;

    @Before
    public void loadOutput() {
        System.out.println("execute before method");
        System.setOut(new PrintStream(this.out));
    }

    @After
    public void backOutput() {
        System.setOut(this.stdout);
        System.out.println("execute after method");
    }

    @Test
    public void whenDrawSquare() {
        new Paint().draw(new Square());
        assertThat(
                new String(out.toByteArray()),
                is(
                        new StringBuilder()
                                .append("@@@@@@\n")
                                .append("@    @\n")
                                .append("@    @\n")
                                .append("@    @\n")
                                .append("@    @\n")
                                .append("@@@@@@\n")
                                .append(System.lineSeparator())
                                .toString()
                )
        );
    }

    @Test
    public void whenDrawTriangle() {
        new Paint().draw(new Triangle());
        assertThat(
                new String(out.toByteArray()),
                is(
                        new StringBuilder()
                                .append("@    \n")
                                .append("@@@  \n")
                                .append("@@@@ \n")
                                .append("@@@@@@\n")
                                .append(System.lineSeparator())
                                .toString()
                )
        );
    }
}
