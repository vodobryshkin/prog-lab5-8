package com.example.server.modules;

import com.example.server.modules.classes.ServerManager;

import java.io.IOException;

public class Server {
    public static void main(String[] args) throws IOException {
        ServerManager serverManager = new ServerManager(9876);
        serverManager.run();
    }
}