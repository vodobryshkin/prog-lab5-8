package com.example.server.modules;

import com.example.server.modules.classes.ServerManager;
import java.io.IOException;
import java.sql.SQLException;
import java.util.concurrent.ForkJoinPool;

public class Server {
    public static void main(String[] args) throws IOException, SQLException {
        ForkJoinPool requestProcessingPool = new ForkJoinPool();
        ServerManager serverManager = new ServerManager(9876, requestProcessingPool);
        serverManager.run();
    }
}