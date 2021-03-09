package ru.job4j.solid.isp.menu;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.assertEquals;

public class ConsoleMenuEngineTest {
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private final PrintStream originalErr = System.err;

    private final MenuElement root = new MenuElement("Task 1");
    private final MenuElement childOne = new MenuElement("A");
    private final MenuElement childTwo = new MenuElement("B");
    private final MenuElement grandsonOne = new MenuElement("a");
    private final MenuElement grandsonTwo = new MenuElement("b");
    private final MenuElement grandsonThree = new MenuElement("a");
    private final MenuElement grandsonFour = new MenuElement("b");

    public ConsoleMenuEngineTest() {
        this.initTree();
    }

    private void initTree() {
        root.addChild(childOne);
        root.addChild(childTwo);
        childOne.addChild(grandsonOne);
        childOne.addChild(grandsonTwo);
        childTwo.addChild(grandsonThree);
        childTwo.addChild(grandsonFour);
    }

    @Before
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));
    }

    @After
    public void restoreStreams() {
        System.setOut(originalOut);
        System.setErr(originalErr);
    }

    @Test
    public void whenShowMenuThanCorrectOutput() {
        ConsoleMenuEngine engine = new ConsoleMenuEngine(root);
        engine.showMenu();

        String expected = "Task 1\n" +
                "---Task 1.A\n" +
                "------Task 1.A.a\n" +
                "------Task 1.A.b\n" +
                "---Task 1.B\n" +
                "------Task 1.B.a\n" +
                "------Task 1.B.b\n";

        assertEquals(expected, outContent.toString());
    }

    @Test
    public void whenSearchElemByTitleThanWasFounded() {
        ConsoleMenuEngine engine = new ConsoleMenuEngine(root);
        MenuElement menuElement = engine.searchMenuElem("Task 1.A.a");
        assertEquals(menuElement.getLevel(), 2);
        assertEquals(menuElement.getName(), "a");
    }
}
