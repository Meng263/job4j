package ru.job4j.io.bot;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author Oleg
 * @since 16.11.2019
 */
public class OracleServer {
    public static final String EXIT = "exit";
    public static final String HELLO_ORACLE = "Hello oracle";
    private Socket socket;

    public OracleServer(int port) {
        initSocket(port);
    }

    public OracleServer(Socket socket) {
        this.socket = socket;
    }

    private void initSocket(int port) {
        try {
            ServerSocket socketServer = new ServerSocket(port);
            System.out.println("Ждем подключения к серверу");
            this.socket = socketServer.accept();
            System.out.println("Подключение состоялось!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    void start() {
        try (PrintWriter outStream = new PrintWriter(socket.getOutputStream(), true);
             BufferedReader inStream = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {
            String response;
            do {
                response = inStream.readLine();
                System.out.println(response);
                if (HELLO_ORACLE.equalsIgnoreCase(response)) {
                    outStream.println("Hello, dear friend, I'm a oracle.");
                    outStream.println();
                } else if (!(EXIT.equalsIgnoreCase(response))) {
                    outStream.println("I'm don't understand");
                    outStream.println();
                }
            } while (!(EXIT.equals(response)));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        OracleServer server = new OracleServer(5000);
        server.start();
    }
}
