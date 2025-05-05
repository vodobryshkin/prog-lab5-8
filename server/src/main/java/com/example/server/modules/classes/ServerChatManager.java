package com.example.server.modules.classes;

import com.example.repository.exceptions.KeyNotFoundException;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;
import java.nio.channels.Selector;
import java.sql.SQLException;
import java.util.concurrent.ForkJoinPool;

public class ServerChatManager implements Runnable {
    private final Selector selector;
    private final ServerKeyAwaiterManager serverKeyAwaiterManager;
    private final ForkJoinPool requestProcessingPool;

    public ServerChatManager(DatagramChannel channel, ByteBuffer buffer, Selector selector,
                             ForkJoinPool requestProcessingPool) throws SQLException {
        this.requestProcessingPool = requestProcessingPool;
        this.serverKeyAwaiterManager = new ServerKeyAwaiterManager(channel, buffer, selector, requestProcessingPool);
        this.selector = selector;
    }

    @Override
    public void run() {
        while (true) {
            try {
                selector.select();
                serverKeyAwaiterManager.awaitKeys();
            } catch (IOException | ClassNotFoundException | KeyNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
    }
}