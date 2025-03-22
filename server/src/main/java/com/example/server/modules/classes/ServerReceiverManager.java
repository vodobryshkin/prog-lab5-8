package com.example.server.modules.classes;

import com.example.server.modules.interfaces.ServerReceiver;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;

public class ServerReceiverManager implements ServerReceiver {
    private final DatagramChannel channel;
    private final ByteBuffer buffer;

    public ServerReceiverManager(DatagramChannel channel, ByteBuffer buffer) {
        this.channel = channel;
        this.buffer = buffer;
    }

    @Override
    public InetSocketAddress receiveMessage() throws IOException {
        buffer.clear();
        InetSocketAddress clientAddress = (InetSocketAddress) channel.receive(buffer);
        buffer.flip();
        return clientAddress;
    }
}
