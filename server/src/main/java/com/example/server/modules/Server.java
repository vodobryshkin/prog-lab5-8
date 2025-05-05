package com.example.server.modules;

import com.example.repository.postgres.adapters.PostgresReader;
import com.example.server.modules.classes.ServerManager;

import java.io.IOException;
import java.sql.SQLException;

public class Server {
    public static void main(String[] args) throws IOException, SQLException {
        ServerManager serverManager = new ServerManager(9876);
        serverManager.run();
    }
}