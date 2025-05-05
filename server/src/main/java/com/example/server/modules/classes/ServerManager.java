package com.example.server.modules.classes;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;
import java.nio.channels.Selector;
import java.sql.SQLException;
import java.util.concurrent.ForkJoinPool;

public class ServerManager implements Runnable {
    private final ServerConnectionManager serverConnectionManager;
    private final ServerChatManager serverChatManager;
    private final DatagramChannel channel;
    private final Selector selector;
    private final ForkJoinPool requestProcessingPool;

    static final Logger logger = LoggerFactory.getLogger(ServerManager.class);

    public ServerManager(int port, ForkJoinPool requestProcessingPool) throws IOException, SQLException {
        this.requestProcessingPool = requestProcessingPool;
        channel = DatagramChannel.open();
        selector = Selector.open();
        ByteBuffer buffer = ByteBuffer.allocate(4096);
        serverConnectionManager = new ServerConnectionManager(port);
        serverChatManager = new ServerChatManager(channel, buffer, selector, requestProcessingPool);
    }

    @Override
    public void run() {
        try {
            serverConnectionManager.connect(channel, selector);
            serverChatManager.run();
        } catch (IOException e) {
            logger.error("Server error: {}", e.getMessage());
            throw new RuntimeException(e);
        }
    }
}