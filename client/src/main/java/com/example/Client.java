package com.example;

import com.example.client.modules.classes.ClientManager;

import java.io.IOException;

public class Client {
    public static void main(String[] args) throws IOException {
        ClientManager clientManager = new ClientManager("localhost", 9876);
        clientManager.run();
    }
}
