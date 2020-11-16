package com.epam.university.java.core.task031;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.LinkedList;

public class ServerImpl implements Server {
    private ServerSocket server;
    private BufferedReader in;
    private final ArrayList<Socket> accepts = new ArrayList<>();
    private final LinkedList<String> messages = new LinkedList<>();

    @Override
    public String readMessage() {
        try {
            if (accepts.isEmpty()) {
                Thread.sleep(50);
            }
            for (Socket s : accepts) {
                in = new BufferedReader(new InputStreamReader(s.getInputStream()));
                while (in.ready()) {
                    messages.add(in.readLine());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (messages.isEmpty()) {
            return "";
        } else {
            return messages.removeLast();
        }
    }

    @Override
    public void start() {
        try {
            server = new ServerSocket(8080);
        } catch (IOException e) {
            e.printStackTrace();
        }

        new Thread(() -> {
            while (!server.isClosed()) {
                try {
                    accepts.add(server.accept());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    @Override
    public void stop() {
        try {
            if (in != null) {
                in.close();
            }
            server.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
