package ru.job4j.io.bot;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

/**
 * @author Oleg
 * @since 16.11.2019
 */
public class OracleClient {
    public static final String EXIT = "exit";
    private final int port;
    private String ipAddress = "";
    private InetAddress inetAddress = null;

    public OracleClient(String ipAddress, int port) {
        this.port = port;
        this.ipAddress = ipAddress;
    }

    public OracleClient(InetAddress inetAddress, int port) {
        this.port = port;
        this.inetAddress = inetAddress;
    }

    private void start() throws IOException {
        Socket socket;
        if (inetAddress != null) {
            socket = new Socket(this.inetAddress, 5000);
        } else {
            socket = new Socket(this.ipAddress, 5000);
        }
        PrintWriter outStream = new PrintWriter(socket.getOutputStream(), true);
        BufferedReader inStream = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        Scanner console = new Scanner(System.in);
        String request;
        String response;
        do {
            request = console.nextLine();
            outStream.println(request);
            response = inStream.readLine();
            while (response != null && !response.isEmpty()) {
                System.out.println(response);
                response = inStream.readLine();
            }
        } while (!EXIT.equalsIgnoreCase(request));
        outStream.close();
        inStream.close();
    }

    public static void main(String[] args) {
        try {
            OracleClient client = new OracleClient(InetAddress.getLocalHost(), 5000);
            client.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
