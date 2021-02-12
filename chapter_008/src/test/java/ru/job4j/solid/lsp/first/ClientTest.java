package ru.job4j.solid.lsp.first;

import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class ClientTest {
    Client client = new Client();

    @Test
    public void rectangleAreaShouldBeCorrect() {
        Rectangle rectangle = new Rectangle();
        assertTrue(client.areaVerifier(rectangle));
    }

    @Test(expected = IllegalStateException.class)
    public void squareAreaShouldThrowException() {
        Rectangle square = new Square();
        client.areaVerifier(square);
    }


}