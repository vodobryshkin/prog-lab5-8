package com.example.server.modules.classes;

import com.example.server.modules.interfaces.ServerSender;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;

public class ServerSenderManager implements ServerSender {
    private final DatagramChannel channel;
    private final ByteBuffer buffer;

    public ServerSenderManager(DatagramChannel channel, ByteBuffer buffer) {
        this.channel = channel;
        this.buffer = buffer;
    }

    @Override
    public void sendMessage(InetSocketAddress address, String message) throws IOException {
        buffer.clear();
        buffer.put(message.getBytes());
        buffer.flip();
        channel.send(buffer, address);

        ServerManager.logger.info("Send response to client {}: {}", address, message);
    }
}
