package ru.job4j.solid.isp.menu;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class MenuElementTest {
    @Test
    public void whenCreateSimpleElementThanCheck() {
        MenuElement first = new MenuElement("Tack 1");
        assertNull(first.getParent());
        assertEquals(first.getLevel(), 0);
        assertEquals(first.getName(), "Tack 1");
    }

    @Test
    public void whenCreateChildAndParentAndCheck() {
        MenuElement parent = new MenuElement("Task 1");
        MenuElement child = new MenuElement("1");
        MenuElement grandson = new MenuElement("1");
        parent.addChild(child);
        child.addChild(grandson);
        assertEquals(child.getTitle(), "Task 1.1");
        assertEquals(grandson.getTitle(), "Task 1.1.1");
    }


}