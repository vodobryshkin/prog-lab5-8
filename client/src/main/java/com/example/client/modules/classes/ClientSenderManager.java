package com.example.client.modules.classes;

import com.example.client.modules.interfaces.ClientSender;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;

public class ClientSenderManager implements ClientSender {
    private final String host;
    private final int port;
    private final DatagramChannel channel;
    private final ByteBuffer buffer;

    public ClientSenderManager(String host, int port, DatagramChannel channel, ByteBuffer buffer) {
        this.host = host;
        this.port = port;
        this.channel = channel;
        this.buffer = buffer;
    }

    @Override
    public void sendMessage(String message) {
        buffer.clear();
        buffer.put(message.getBytes());
        buffer.flip();
        try {
            channel.send(buffer, new InetSocketAddress(host, port));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
