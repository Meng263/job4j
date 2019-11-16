package ru.job4j.io.bot;

import com.google.common.base.Joiner;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.Socket;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class OracleServerTest {
    public static final String LN = System.lineSeparator();

    @Test
    public void whenRequestExitThanResponseEmptyAndExit() throws IOException {
        serverTest("exit", "");
    }

    @Test
    public void whenRequestHelloThanResponseHello() throws IOException {
        String request = Joiner.on(LN).join("hello oracle", "exit");
        String expectedResponse = "Hello, dear friend, I'm a oracle.".concat(LN).concat(LN);
        serverTest(request, expectedResponse);
    }

    @Test
    public void whenRequestUnsupportedThanResponseDontUnderstand() throws IOException {
        String request = Joiner.on(LN).join("another string", "exit");
        String expectedResponse = "I'm don't understand".concat(LN).concat(LN);
        serverTest(request, expectedResponse);
    }

    private void serverTest(String request, String expectedResponse) throws IOException {
        Socket socket = mock(Socket.class);
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        ByteArrayInputStream inStream = new ByteArrayInputStream(request.getBytes());
        when(socket.getInputStream()).thenReturn(inStream);
        when(socket.getOutputStream()).thenReturn(outStream);
        OracleServer server = new OracleServer(socket);
        server.start();
        assertThat(outStream.toString(), is(expectedResponse));
    }
}