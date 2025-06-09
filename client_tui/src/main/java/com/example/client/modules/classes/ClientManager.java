package com.example.client.modules.classes;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;

public class ClientManager implements Runnable {
    private final ClientConnectionManager clientConnectionManager;
    private final ClientChatManager clientChatManager;
    private final DatagramChannel channel;
    private final ByteBuffer buffer;

    public ClientManager(String host, int port) throws IOException {
        channel = DatagramChannel.open();
        buffer = ByteBuffer.allocate(4096);
        clientChatManager = new ClientChatManager(host, port, channel, buffer);
        clientConnectionManager = new ClientConnectionManager(host, port);
    }

    @Override
    public void run() {
        try {
            clientConnectionManager.connect(channel, buffer);
            clientChatManager.run();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}