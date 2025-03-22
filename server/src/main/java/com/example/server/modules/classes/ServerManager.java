package com.example.server.modules.classes;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;
import java.nio.channels.Selector;

public class ServerManager implements Runnable {
    private final ServerConnectionManager serverConnectionManager;
    private final ServerChatManager serverChatManager;
    private final DatagramChannel channel;
    private final Selector selector;
    static final Logger logger = LoggerFactory.getLogger(ServerManager.class);

    public ServerManager(int port) throws IOException {
        channel = DatagramChannel.open();
        selector = Selector.open();
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        serverConnectionManager = new ServerConnectionManager(port);
        serverChatManager = new ServerChatManager(channel, buffer, selector);
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
