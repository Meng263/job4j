package ru.job4j.solid.lsp.second;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;


public class TaskTest {
    @Test
    public void taskShouldBeClosedNormally() {
        Task task = new Task();
        task.close();
        assertThat(task.getStatus(), is(Status.CLOSED));
    }

    @Test(expected = IllegalStateException.class)
    public void projectTaskShouldThrowException() {
        Task task = new ProjectTask();
        task.close();
    }
}