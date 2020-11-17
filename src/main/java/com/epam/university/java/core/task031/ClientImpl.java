package com.epam.university.java.core.task031;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.InetAddress;
import java.net.Socket;

public class ClientImpl implements Client {
    private Socket client;
    private BufferedWriter out;
    private boolean check = false;

    @Override
    public void sendMessage(String message) {
        if (message == null) {
            check = true;
        }
        try {
            out = new BufferedWriter(new OutputStreamWriter(client.getOutputStream()));
            out.write(message + System.lineSeparator());
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void start() {
        try {
            client = new Socket(InetAddress.getLocalHost(), 8080);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void stop() {
        try {
            if (out != null) {
                out.close();
            }
            client.close();
            if (check) {
                throw new IllegalArgumentException();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
